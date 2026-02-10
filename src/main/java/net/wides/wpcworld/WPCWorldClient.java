package net.wides.wpcworld;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.screen.AssemblyTableScreen;
import net.wides.wpcworld.screen.CobaltBlastFurnaceScreen;
import net.wides.wpcworld.screen.FoundryFurnaceControllerScreen;
import net.wides.wpcworld.screen.ModScreenHandlers;

public class WPCWorldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ScreenRegistry.register(ModScreenHandlers.FOUNDRY_FURNACE_CONTROLLER_SCREEN_HANDLER,
                FoundryFurnaceControllerScreen::new);

        ScreenRegistry.register(ModScreenHandlers.COBALT_BLAST_FURNACE_SCREEN_HANDLER,
                CobaltBlastFurnaceScreen::new);

        ScreenRegistry.register(ModScreenHandlers.ASSEMBLY_TABLE_SCREEN_HANDLER,
                AssemblyTableScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(
                ModBlocks.COBALT_GRATE,
                RenderLayer.getCutout()
        );

        BlockRenderLayerMap.INSTANCE.putBlock(
                ModBlocks.DUALBLOOM_CROP,
                RenderLayer.getCutout()
        );

        BlockRenderLayerMap.INSTANCE.putBlock(
                ModBlocks.DIAMONDBLOOM_CROP,
                RenderLayer.getCutout()
        );
    }
}
