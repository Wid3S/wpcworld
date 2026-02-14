package net.wides.wpcworld.compat.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.recipe.AssemblingRecipe;
import net.wides.wpcworld.recipe.CobaltBlastingRecipe;
import net.wides.wpcworld.recipe.FoundrySmeltingRecipe;
import net.wides.wpcworld.screen.AssemblyTableScreen;
import net.wides.wpcworld.screen.CobaltBlastFurnaceScreen;
import net.wides.wpcworld.screen.FoundryFurnaceControllerScreen;

public class WPCWorldModREIClientPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new FoundrySmeltingCategory());
        registry.add(new CobaltBlastingCategory());
        registry.add(new AssemblingCategory());

        registry.addWorkstations(FoundrySmeltingCategory.FOUNDRY_SMELTING, EntryStacks.of(ModBlocks.FOUNDRY_FURNACE_CONTROLLER));
        registry.addWorkstations(CobaltBlastingCategory.COBALT_BLASTING, EntryStacks.of(ModBlocks.COBALT_BLAST_FURNACE));
        registry.addWorkstations(AssemblingCategory.ASSEMBLING, EntryStacks.of(ModBlocks.ASSEMBLY_TABLE));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(FoundrySmeltingRecipe.class, FoundrySmeltingRecipe.Type.INSTANCE,
                FoundrySmeltingDisplay::new);

        registry.registerRecipeFiller(CobaltBlastingRecipe.class, CobaltBlastingRecipe.Type.INSTANCE,
                CobaltBlastingDisplay::new);

        registry.registerRecipeFiller(AssemblingRecipe.class, AssemblingRecipe.Type.INSTANCE,
                AssemblingDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 27, 21, 13),
                FoundryFurnaceControllerScreen.class,
                FoundrySmeltingCategory.FOUNDRY_SMELTING);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 27, 21, 13),
                CobaltBlastFurnaceScreen.class,
                CobaltBlastingCategory.COBALT_BLASTING);

        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 98,
                        ((screen.height - 166) / 2), 21, 13),
                AssemblyTableScreen.class,
                AssemblingCategory.ASSEMBLING);
    }
}
