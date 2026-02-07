package net.wides.wpcworld.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;

public class CobaltEmiCategories {

    public static final EmiRecipeCategory COBALT_BLASTING =
            new EmiRecipeCategory(
                    new Identifier(WPCWorld.MOD_ID, "cobalt_blasting"),
                    EmiStack.of(ModBlocks.COBALT_BLAST_FURNACE)
            );

    private CobaltEmiCategories() {
    }
}