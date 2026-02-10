package net.wides.wpcworld.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.recipe.AssemblingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AssemblingRecipeBuilder {

    private final List<String> pattern = new ArrayList<>();
    private final Map<Character, Ingredient> inputs = new HashMap<>();
    private final Item output;
    private int count = 1;
    private int energy = 0;
    private final Advancement.Builder advancement = Advancement.Builder.create();

    private AssemblingRecipeBuilder(ItemConvertible output) {
        this.output = output.asItem();
    }

    public static AssemblingRecipeBuilder create(ItemConvertible output) {
        return new AssemblingRecipeBuilder(output);
    }

    public AssemblingRecipeBuilder pattern(String row) {
        if (row.length() > 5) throw new IllegalArgumentException("Pattern row cannot be wider than 5");
        pattern.add(row);
        if (pattern.size() > 5) throw new IllegalArgumentException("Pattern height cannot be more than 5");
        return this;
    }

    public AssemblingRecipeBuilder input(char c, ItemConvertible item) {
        inputs.put(c, Ingredient.ofItems(item));
        return this;
    }

    public AssemblingRecipeBuilder count(int count) {
        this.count = count;
        return this;
    }

    public AssemblingRecipeBuilder energyCost(int energy) {
        this.energy = energy;
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter) {
        Identifier outputId = Registries.ITEM.getId(output);
        Identifier id = new Identifier(WPCWorld.MOD_ID, outputId.getPath() + "_assembling");

        advancement.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(id))
                .rewards(AdvancementRewards.Builder.recipe(id));

        exporter.accept(new Provider(id));
    }

    private class Provider implements RecipeJsonProvider {

        private final Identifier id;

        Provider(Identifier id) {
            this.id = id;
        }

        @Override
        public void serialize(JsonObject json) {
            json.addProperty("type", WPCWorld.MOD_ID + ":" + AssemblingRecipe.Type.ID);

            JsonArray patternArray = new JsonArray();
            for (String row : pattern) patternArray.add(row);
            json.add("pattern", patternArray);

            JsonObject keyObj = new JsonObject();
            for (Map.Entry<Character, Ingredient> entry : inputs.entrySet()) {
                keyObj.add(entry.getKey().toString(), entry.getValue().toJson());
            }
            json.add("key", keyObj);

            JsonObject result = new JsonObject();
            result.addProperty("item", Registries.ITEM.getId(output).toString());
            result.addProperty("count", count);
            json.add("result", result);

            json.addProperty("energy", energy);
        }

        @Override
        public Identifier getRecipeId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return AssemblingRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject toAdvancementJson() {
            return advancement.toJson();
        }

        @Nullable
        @Override
        public Identifier getAdvancementId() {
            return new Identifier(id.getNamespace(), "recipes/" + id.getPath());
        }
    }
}