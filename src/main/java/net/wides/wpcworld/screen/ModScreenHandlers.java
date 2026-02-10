package net.wides.wpcworld.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;

public class ModScreenHandlers {
    public static final ScreenHandlerType<FoundryFurnaceControllerScreenHandler> FOUNDRY_FURNACE_CONTROLLER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(WPCWorld.MOD_ID, "foundry_furnace_controller_screen_handler"),
                    new ExtendedScreenHandlerType<>(FoundryFurnaceControllerScreenHandler::new));

    public static final ScreenHandlerType<CobaltBlastFurnaceScreenHandler> COBALT_BLAST_FURNACE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(WPCWorld.MOD_ID, "cobalt_blast_furnace_screen_handler"),
                    new ExtendedScreenHandlerType<>(CobaltBlastFurnaceScreenHandler::new));

    public static final ScreenHandlerType<AssemblyTableScreenHandler> ASSEMBLY_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(WPCWorld.MOD_ID, "assembly_table_screen_handler"),
                    new ExtendedScreenHandlerType<>(AssemblyTableScreenHandler::new));


    public static void registerScreenHandler() {
        WPCWorld.LOGGER.info("Registering Screen Handlers for " + WPCWorld.MOD_ID);
    }
}
