package net.wides.wpcworld.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.recipe.CobaltBlastingRecipe;
import net.wides.wpcworld.recipe.FoundrySmeltingRecipe;

public class WPCWorldEmiPlugin implements EmiPlugin {

    public static final EmiRecipeCategory FOUNDRY_SMELTING =
            new EmiRecipeCategory(
                    new Identifier(WPCWorld.MOD_ID, "foundry_smelting"),
                    EmiStack.of(ModBlocks.FOUNDRY_FURNACE_CONTROLLER)
            );

    public static final EmiRecipeCategory COBALT_BLASTING =
            new EmiRecipeCategory(
                    new Identifier(WPCWorld.MOD_ID, "cobalt_blasting"),
                    EmiStack.of(ModBlocks.COBALT_BLAST_FURNACE)
            );

    @Override
    public void register(EmiRegistry registry) {

        registry.addCategory(FoundryEmiCategories.FOUNDRY_SMELTING);
        registry.addCategory(CobaltEmiCategories.COBALT_BLASTING);

        registry.addWorkstation(
                FoundryEmiCategories.FOUNDRY_SMELTING,
                EmiStack.of(ModBlocks.FOUNDRY_FURNACE_CONTROLLER));

        registry.addWorkstation(
                CobaltEmiCategories.COBALT_BLASTING,
                EmiStack.of(ModBlocks.COBALT_BLAST_FURNACE)
        );

        registry.getRecipeManager()
                .listAllOfType(FoundrySmeltingRecipe.Type.INSTANCE)
                .forEach(recipe ->
                        registry.addRecipe(new FoundrySmeltingEmiRecipe(recipe))
                );

        registry.getRecipeManager()
                .listAllOfType(CobaltBlastingRecipe.Type.INSTANCE)
                .forEach(recipe ->
                        registry.addRecipe(new CobaltBlastingEmiRecipe(recipe))
                );
    }
}