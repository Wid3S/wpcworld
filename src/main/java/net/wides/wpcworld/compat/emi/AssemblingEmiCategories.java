package net.wides.wpcworld.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;

public class AssemblingEmiCategories {

    public static final EmiRecipeCategory ASSEMBLING =
            new EmiRecipeCategory(
                    new Identifier(WPCWorld.MOD_ID, "assembling"),
                    EmiStack.of(ModBlocks.ASSEMBLY_TABLE)
            );

    private AssemblingEmiCategories() {
    }
}