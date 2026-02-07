package net.wides.wpcworld.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
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

public class CobaltBlastingRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final Ingredient input;
    private final int inputCount;
    private final ItemStack output;
    private final int outputCount;
    private final int cookTime;
    private final float experience;

    public CobaltBlastingRecipe(
            Identifier id,
            Ingredient input,
            int inputCount,
            ItemStack output,
            int outputCount,
            int cookTime,
            float experience
    ) {
        this.id = id;
        this.input = input;
        this.inputCount = inputCount;
        this.output = output;
        this.outputCount = outputCount;
        this.cookTime = cookTime;
        this.experience = experience;
    }

    @Override
    public boolean matches(SimpleInventory inv, World world) {
        ItemStack stack = inv.getStack(0);
        return input.test(stack) && stack.getCount() >= inputCount;
    }

    @Override
    public ItemStack craft(SimpleInventory inv, DynamicRegistryManager mgr) {
        ItemStack result = output.copy();
        result.setCount(outputCount);
        return result;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager mgr) {
        ItemStack result = output.copy();
        result.setCount(outputCount);
        return result;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return DefaultedList.ofSize(1, input);
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    public int getInputCount() {
        return inputCount;
    }

    public int getOutputCount() {
        return outputCount;
    }

    public int getCookTime() {
        return cookTime;
    }

    public float getExperience() {
        return experience;
    }

    @Override public Identifier getId() { return id; }
    @Override public RecipeSerializer<?> getSerializer() { return Serializer.INSTANCE; }
    @Override public RecipeType<?> getType() { return Type.INSTANCE; }

    public static class Type implements RecipeType<CobaltBlastingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "cobalt_blasting";
    }

    public static class Serializer implements RecipeSerializer<CobaltBlastingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "cobalt_blasting";

        @Override
        public CobaltBlastingRecipe read(Identifier id, JsonObject json) {

            Ingredient input = Ingredient.fromJson(json.get("ingredient"));
            int inputCount = JsonHelper.getInt(json, "ingredient_count");

            JsonObject result = JsonHelper.getObject(json, "result");
            ItemStack output = new ItemStack(
                    Registries.ITEM.get(new Identifier(JsonHelper.getString(result, "item")))
            );
            int outputCount = JsonHelper.getInt(result, "count");

            int cookTime = JsonHelper.getInt(json, "cook_time");
            float exp = JsonHelper.getFloat(json, "experience", 0.0f);

            return new CobaltBlastingRecipe(
                    id,
                    input,
                    inputCount,
                    output,
                    outputCount,
                    cookTime,
                    exp
            );
        }

        @Override
        public CobaltBlastingRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            int inputCount = buf.readInt();
            ItemStack output = buf.readItemStack();
            int outputCount = buf.readInt();
            int cookTime = buf.readInt();
            float exp = buf.readFloat();

            return new CobaltBlastingRecipe(
                    id,
                    input,
                    inputCount,
                    output,
                    outputCount,
                    cookTime,
                    exp
            );
        }

        @Override
        public void write(PacketByteBuf buf, CobaltBlastingRecipe recipe) {
            recipe.input.write(buf);
            buf.writeInt(recipe.inputCount);
            buf.writeItemStack(recipe.output);
            buf.writeInt(recipe.outputCount);
            buf.writeInt(recipe.cookTime);
            buf.writeFloat(recipe.experience);
        }
    }
}