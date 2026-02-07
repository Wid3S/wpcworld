package net.wides.wpcworld.datagen.recipe;

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
import net.wides.wpcworld.recipe.CobaltBlastingRecipe;
import net.wides.wpcworld.recipe.FoundrySmeltingRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class CobaltBlastingRecipeBuilder {

    private final Ingredient input;
    private final int inputCount;
    private final Item output;
    private final int outputCount;
    private final int cookTime;
    private final float experience;

    private final Advancement.Builder advancement = Advancement.Builder.create();

    private CobaltBlastingRecipeBuilder(
            Ingredient input,
            int inputCount,
            ItemConvertible output,
            int outputCount,
            int cookTime,
            float experience
    ) {
        this.input = input;
        this.inputCount = inputCount;
        this.output = output.asItem();
        this.outputCount = outputCount;
        this.cookTime = cookTime;
        this.experience = experience;
    }

    public static CobaltBlastingRecipeBuilder smelting(
            ItemConvertible input,
            int inputCount,
            ItemConvertible output,
            int outputCount,
            int cookTime,
            float experience
    ) {
        return new CobaltBlastingRecipeBuilder(
                Ingredient.ofItems(input),
                inputCount,
                output,
                outputCount,
                cookTime,
                experience
        );
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter) {

        Identifier inputId = Registries.ITEM.getId(
                input.getMatchingStacks()[0].getItem()
        );

        Identifier outputId = Registries.ITEM.getId(output);

        Identifier id = new Identifier(
                WPCWorld.MOD_ID,
                outputId.getPath()
                        + "_cobalt_blasting_from_"
                        + inputId.getPath()
        );

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

            json.addProperty("type", WPCWorld.MOD_ID + ":" + CobaltBlastingRecipe.Type.ID);

            json.add("ingredient", input.toJson());
            json.addProperty("ingredient_count", inputCount);

            JsonObject result = new JsonObject();
            result.addProperty("item", Registries.ITEM.getId(output).toString());
            result.addProperty("count", outputCount);
            json.add("result", result);

            json.addProperty("cook_time", cookTime);
            json.addProperty("experience", experience);
        }

        @Override
        public Identifier getRecipeId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return CobaltBlastingRecipe.Serializer.INSTANCE;
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
