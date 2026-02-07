package net.wides.wpcworld.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.recipe.FoundrySmeltingRecipe;

import java.util.List;

public class FoundrySmeltingEmiRecipe implements EmiRecipe {

    private static final EmiTexture BACKGROUND =
            new EmiTexture(new Identifier(WPCWorld.MOD_ID,
                    "textures/gui/foundry_furnace_controller_recipe_gui.png"),
                    0, 0, 176, 91);

    private static final int BAR_SRC_X = 176;
    private static final int BAR_SRC_Y = 18;
    private static final int BAR_WIDTH = 16;
    private static final int BAR_HEIGHT = 57;

    private static final int BAR_DRAW_X = 4;
    private static final int BAR_DRAW_Y = 13;

    private static final EmiTexture BAR_TEXTURE =
            new EmiTexture(new Identifier(WPCWorld.MOD_ID,
                    "textures/gui/foundry_furnace_controller_recipe_gui.png"),
                    BAR_SRC_X, BAR_SRC_Y, BAR_WIDTH, BAR_HEIGHT);

    private final FoundrySmeltingRecipe recipe;

    public FoundrySmeltingEmiRecipe(FoundrySmeltingRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return FoundryEmiCategories.FOUNDRY_SMELTING;
    }

    @Override
    public Identifier getId() {
        return recipe.getId();
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(EmiIngredient.of(recipe.getIngredients().get(0)));
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(EmiStack.of(recipe.getOutput(null)));
    }

    @Override
    public int getDisplayWidth() {
        return 168;
    }

    @Override
    public int getDisplayHeight() {
        return 83;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BACKGROUND, -4, -4);

        widgets.addTexture(BAR_TEXTURE, BAR_DRAW_X, BAR_DRAW_Y);

        widgets.addSlot(getInputs().get(0), 52, 35);
        widgets.addSlot(getOutputs().get(0), 99, 35).recipeContext(this).drawBack(false);

        widgets.addFillingArrow(71, 36, 1280);

        widgets.addTexture(EmiTexture.FULL_FLAME, 27, 37);
    }
}