package net.wides.wpcworld.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class AssemblingRecipe implements Recipe<Inventory> {

    private final Identifier id;
    private final int width;
    private final int height;
    private final DefaultedList<Ingredient> input;
    private final ItemStack output;
    private final int energyCost;

    public AssemblingRecipe(Identifier id, int width, int height, DefaultedList<Ingredient> input, ItemStack output, int energyCost) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.input = input;
        this.output = output;
        this.energyCost = energyCost;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getEnergyCost() { return energyCost; }

    @Override
    public boolean matches(Inventory inv, World world) {
        if (world.isClient) return false;

        for (int startY = 0; startY <= 5 - height; startY++) {
            for (int startX = 0; startX <= 5 - width; startX++) {
                if (checkMatch(inv, startX, startY)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean checkMatch(Inventory inv, int offsetX, int offsetY) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {

                int slotIndex = x + y * 5;
                ItemStack stack = inv.getStack(slotIndex);

                boolean insidePattern =
                        x >= offsetX && x < offsetX + width &&
                                y >= offsetY && y < offsetY + height;

                if (insidePattern) {
                    int recipeIndex = (y - offsetY) * width + (x - offsetX);
                    Ingredient ingredient = input.get(recipeIndex);

                    if (!ingredient.test(stack)) {
                        return false;
                    }
                } else {
                    if (!stack.isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(Inventory inv, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int invWidth, int invHeight) {
        return invWidth >= width && invHeight >= height;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return input;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<AssemblingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "assembling";
    }

    public static class Serializer implements RecipeSerializer<AssemblingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "assembling";

        @Override
        public AssemblingRecipe read(Identifier id, JsonObject json) {
            if (!json.has("pattern") || !json.has("key") || !json.has("result")) throw new JsonParseException("Assembling recipe must contain pattern, key and result");
            JsonArray patternArray = json.getAsJsonArray("pattern");
            int height = patternArray.size();
            if (height < 1 || height > 5) throw new JsonParseException("Invalid pattern height for assembling recipe (must be 1..5)");
            int width = 0;
            for (int i = 0; i < height; i++) {
                String line = patternArray.get(i).getAsString();
                if (line.length() > 5) throw new JsonParseException("Pattern row too wide (max 5)");
                if (line.length() > width) width = line.length();
            }
            if (width < 1) width = 1;
            for (int i = 0; i < height; i++) {
                String line = patternArray.get(i).getAsString();
                if (line.length() != width) {
                    throw new JsonParseException(
                            "All pattern rows must have the same length (expected " + width + ", got " + line.length() + ")"
                    );
                }
            }

            JsonObject key = json.getAsJsonObject("key");
            int energy = JsonHelper.getInt(json, "energy", 0);
            DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
            for (int y = 0; y < height; y++) {
                String line = patternArray.get(y).getAsString();
                for (int x = 0; x < width; x++) {
                    char c = x < line.length() ? line.charAt(x) : ' ';
                    if (c == ' ') {
                        ingredients.set(y * width + x, Ingredient.EMPTY);
                    } else {
                        String keyChar = String.valueOf(c);
                        if (!key.has(keyChar)) throw new JsonParseException("Key '" + keyChar + "' not found in recipe key");
                        ingredients.set(y * width + x, Ingredient.fromJson(key.get(keyChar)));
                    }
                }
            }
            JsonObject resultObj = JsonHelper.getObject(json, "result");
            Identifier resultId = new Identifier(JsonHelper.getString(resultObj, "item"));
            int count = JsonHelper.getInt(resultObj, "count", 1);
            ItemStack output = new ItemStack(Registries.ITEM.get(resultId));
            output.setCount(count);
            return new AssemblingRecipe(id, width, height, ingredients, output, energy);
        }

        @Override
        public AssemblingRecipe read(Identifier id, PacketByteBuf buf) {
            int width = buf.readInt();
            int height = buf.readInt();
            int energy = buf.readInt();
            DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
            for (int i = 0; i < width * height; i++) ingredients.set(i, Ingredient.fromPacket(buf));
            ItemStack output = buf.readItemStack();
            return new AssemblingRecipe(id, width, height, ingredients, output, energy);
        }

        @Override
        public void write(PacketByteBuf buf, AssemblingRecipe recipe) {
            buf.writeInt(recipe.width);
            buf.writeInt(recipe.height);
            buf.writeInt(recipe.energyCost);
            for (Ingredient ingredient : recipe.input) ingredient.write(buf);
            buf.writeItemStack(recipe.output);
        }
    }
}