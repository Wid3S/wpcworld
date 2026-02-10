package net.wides.wpcworld.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.item.ModItems;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        addDrop(ModBlocks.RAW_LITHIUM_BLOCK);
        addDrop(ModBlocks.LITHIUM_BLOCK);

        addDrop(ModBlocks.RAW_COBALT_BLOCK);
        addDrop(ModBlocks.COBALT_BLOCK);
        addDrop(ModBlocks.COBALT_GRATE);

        addDrop(ModBlocks.HEAT_RESISTANT_BRICKS);
        addDrop(ModBlocks.HEAT_RESISTANT_PILLAR);
        addDrop(ModBlocks.FOUNDRY_FURNACE_CONTROLLER);

        addDrop(ModBlocks.AIR_INTAKE);
        addDrop(ModBlocks.COBALT_BLAST_FURNACE);

        addDrop(ModBlocks.RAW_TUNGSTEN_BLOCK);
        addDrop(ModBlocks.TUNGSTEN_BLOCK);

        addDrop(ModBlocks.ASSEMBLY_TABLE);

        addDrop(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE, oreDrops(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE, ModItems.RAW_LITHIUM));
        addDrop(ModBlocks.LEPIDOLITE_ORE, oreDrops(ModBlocks.LEPIDOLITE_ORE, ModItems.RAW_LITHIUM));

        addDrop(ModBlocks.COBALT_ORE, oreDrops(ModBlocks.COBALT_ORE, ModItems.RAW_COBALT));
        addDrop(ModBlocks.DEEPSLATE_COBALT_ORE, oreDrops(ModBlocks.DEEPSLATE_COBALT_ORE, ModItems.RAW_COBALT));

        addDrop(ModBlocks.DEEPSLATE_TUNGSTEN_ORE, oreDrops(ModBlocks.DEEPSLATE_TUNGSTEN_ORE, ModItems.RAW_TUNGSTEN));

        addDrop(ModBlocks.DEEPSLATE_ALBITE_ORE, oreDrops(ModBlocks.DEEPSLATE_ALBITE_ORE, ModItems.ALBITE_CRYSTAL));
    }
}
