package net.wides.wpcworld.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.custom.*;

public class ModBlocks {
    public static final Block LEPIDOLITE_ORE = registerBlock("lepidolite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.GOLD_ORE).strength(3.0f, 3.0f), UniformIntProvider.create(1, 3)));
    public static final Block DEEPSLATE_LEPIDOLITE_ORE = registerBlock("deepslate_lepidolite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_GOLD_ORE).strength(4.5f, 3.0f), UniformIntProvider.create(1, 3)));
    public static final Block RAW_LITHIUM_BLOCK = registerBlock("raw_lithium_block",
            new Block(FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK).strength(5.0f, 6.0f)));
    public static final Block LITHIUM_BLOCK = registerBlock("lithium_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(5.0f, 6.0f)));

    public static final Block HEAT_RESISTANT_BRICKS = registerBlock("heat_resistant_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.BRICKS).strength(4.0f, 6.0f)));
    public static final Block HEAT_RESISTANT_PILLAR = registerBlock("heat_resistant_pillar",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.QUARTZ_PILLAR).strength(4.0f, 6.0f)));
    public static final Block FOUNDRY_FURNACE_CONTROLLER = registerBlock("foundry_furnace_controller",
            new FoundryFurnaceControllerBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).strength(3.5f, 6.0f)
                    .luminance(state -> state.get(FoundryFurnaceControllerBlock.LIT) ? 13 : 0)));

    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(5.0f, 6.0f), UniformIntProvider.create(2, 4)));
    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_DIAMOND_ORE).strength(6.0f, 6.0f), UniformIntProvider.create(2, 4)));
    public static final Block RAW_COBALT_BLOCK = registerBlock("raw_cobalt_block",
            new Block(FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK).strength(6.0f, 6.0f)));
    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).strength(6.0f, 6.0f)));
    public static final Block COBALT_GRATE = registerBlock("cobalt_grate",
            new Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK).strength(5.0f, 5.0f).nonOpaque()));

    public static final Block COBALT_BLAST_FURNACE = registerBlock("cobalt_blast_furnace",
            new CobaltBlastFurnaceBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).strength(5.0f, 5.0f)
                    .luminance(state -> state.get(CobaltBlastFurnaceBlock.LIT) ? 13 : 0)));
    public static final Block AIR_INTAKE = registerBlock("air_intake",
            new AirIntakeBlock(FabricBlockSettings.copyOf(Blocks.BRICKS).strength(5.0f, 5.0f)));

    public static final Block DEEPSLATE_ALBITE_ORE = registerBlock("deepslate_albite_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(8.0f, 8.0f), UniformIntProvider.create(7, 9)));

    public static final Block DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).strength(7.0f, 7.0f), UniformIntProvider.create(3, 5)));

    public static final Block DUALBLOOM_CROP = registerBlockWithoutBlockItem("dualbloom_crop",
            new DualBloomCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block DIAMONDBLOOM_CROP = registerBlockWithoutBlockItem("diamondbloom_crop",
            new DiamondBloomCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(WPCWorld.MOD_ID, name), block);
    }
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(WPCWorld.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(WPCWorld.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void  registerModBlocks() {
        WPCWorld.LOGGER.info("Registering Mod Blocks for " + WPCWorld.MOD_ID);
    }
}
