package net.wides.wpcworld.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LEPIDOLITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_LITHIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LITHIUM_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HEAT_RESISTANT_BRICKS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_COBALT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_ALBITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CURSED_WORM, Models.GENERATED);


        itemModelGenerator.register(ModItems.SEA_TYRAN_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_TYRAN_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_TYRAN_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_TYRAN_BOOTS, Models.GENERATED);


        itemModelGenerator.register(ModItems.RAW_LITHIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.LITHIUM_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.LITHIUM_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.LITHIUM_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.LITHIUM_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LITHIUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LITHIUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LITHIUM_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LITHIUM_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.HEAT_RESISTANT_BRICK, Models.GENERATED);

        itemModelGenerator.register(ModItems.RAW_COBALT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.COBALT_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.COKE, Models.GENERATED);

        itemModelGenerator.register(ModItems.DIAMOND_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.COMPRESSED_DIAMOND, Models.GENERATED);
        itemModelGenerator.register(ModItems.DIAMOND_PEARL, Models.GENERATED);

        itemModelGenerator.register(ModItems.DIAMOND_CARROT, Models.GENERATED);

        itemModelGenerator.register(ModItems.DUALBLOOM_LEAF, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUALBLOOM_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUAL_ENERGY_CORE, Models.GENERATED);

        itemModelGenerator.register(ModItems.ALBITE_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.RESONANT_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALBITE_STAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARTIFACT_BASE, Models.GENERATED);

        itemModelGenerator.register(ModItems.SPIKY_RECHARGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PRISM_OF_THE_SEAS, Models.GENERATED);
    }
}
