package net.wides.wpcworld.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;

public class FoundryEmiCategories {

    public static final EmiRecipeCategory FOUNDRY_SMELTING =
            new EmiRecipeCategory(
                    new Identifier(WPCWorld.MOD_ID, "foundry_smelting"),
                    EmiStack.of(ModBlocks.FOUNDRY_FURNACE_CONTROLLER)
            );

    private FoundryEmiCategories() {
    }
}