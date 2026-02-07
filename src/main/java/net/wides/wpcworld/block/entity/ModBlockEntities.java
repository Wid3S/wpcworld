package net.wides.wpcworld.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.wides.wpcworld.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;

public class ModBlockEntities {
    public static final BlockEntityType<FoundryFurnaceControllerBlockEntity> FOUNDRY_FURNACE_CONTROLLER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(WPCWorld.MOD_ID, "foundry_furnace_controller_block_entity"),
                    FabricBlockEntityTypeBuilder.create(FoundryFurnaceControllerBlockEntity::new,
                            ModBlocks.FOUNDRY_FURNACE_CONTROLLER).build(null));

    public static final BlockEntityType<CobaltBlastFurnaceBlockEntity> COBALT_BLAST_FURNACE_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(WPCWorld.MOD_ID, "cobalt_blast_furnace_block_entity"),
                    FabricBlockEntityTypeBuilder.create(CobaltBlastFurnaceBlockEntity::new,
                            ModBlocks.COBALT_BLAST_FURNACE).build(null));

    public static void registerBlockEntities() {
        WPCWorld.LOGGER.info("Registering Block Entities for " + WPCWorld.MOD_ID);
    }
}
