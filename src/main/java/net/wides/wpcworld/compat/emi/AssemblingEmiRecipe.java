package net.wides.wpcworld.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.client.gui.tooltip.*;
import net.minecraft.client.gui.widget.*;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.recipe.AssemblingRecipe;

import java.util.ArrayList;
import java.util.List;

public class AssemblingEmiRecipe implements EmiRecipe {

    private static final Identifier TEX_ID = new Identifier(WPCWorld.MOD_ID, "textures/gui/assembly_table_recipe_gui.png");

    private static final EmiTexture BACKGROUND = new EmiTexture(TEX_ID, 0, 0, 176, 106);

    private static final int BAR_SRC_X = 176;
    private static final int BAR_SRC_Y = 0;
    private static final int BAR_WIDTH = 16;
    private static final int BAR_HEIGHT = 57;

    private static final int BAR_DRAW_X = 144;
    private static final int BAR_DRAW_Y = 24;

    private static final int GHOST_X = 122;
    private static final int GHOST_Y = 43;

    private static final EmiTexture BAR_TEXTURE =
            new EmiTexture(new Identifier(WPCWorld.MOD_ID,
                    "textures/gui/assembly_table_recipe_gui.png"),
                    BAR_SRC_X, BAR_SRC_Y, BAR_WIDTH, BAR_HEIGHT);

    private final AssemblingRecipe recipe;
    private final List<EmiIngredient> inputs;
    private final EmiStack ghostStack;

    public AssemblingEmiRecipe(AssemblingRecipe recipe, ItemStack ghostItem) {
        this.recipe = recipe;
        this.inputs = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            if (i < recipe.getIngredients().size()) {
                inputs.add(EmiIngredient.of(recipe.getIngredients().get(i)));
            } else {
                inputs.add(EmiIngredient.of(Ingredient.EMPTY));
            }
        }
        ItemStack ghost = ghostItem.copy();
        ghost.setCount(recipe.getEnergyCost()/20);
        this.ghostStack = EmiStack.of(ghost);
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return AssemblingEmiCategories.ASSEMBLING;
    }

    @Override
    public Identifier getId() {
        return recipe.getId();
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
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
        return 106;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BACKGROUND, -4, -4);

        int energy = recipe.getEnergyCost();
        int maxEnergy = 1000;
        int filled = Math.min(energy * BAR_HEIGHT / maxEnergy, BAR_HEIGHT);
        if (filled > 0) {
            EmiTexture partial = new EmiTexture(TEX_ID, BAR_SRC_X, BAR_SRC_Y + (BAR_HEIGHT - filled), BAR_WIDTH, filled);
            widgets.addTexture(partial, BAR_DRAW_X, BAR_DRAW_Y + (BAR_HEIGHT - filled));
        }

        int index = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                widgets.addSlot(inputs.get(index++), 7 + col * 18, 7 + row * 18);
            }
        }

        widgets.addSlot(getOutputs().get(0), 101, 43).recipeContext(this).drawBack(false);

        widgets.addSlot(ghostStack, GHOST_X, GHOST_Y).drawBack(true);
    }
}
