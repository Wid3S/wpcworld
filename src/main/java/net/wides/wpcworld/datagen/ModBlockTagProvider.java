package net.wides.wpcworld.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.HEAT_RESISTANT_BLOCKS)
                .add(ModBlocks.HEAT_RESISTANT_BRICKS,
                        ModBlocks.HEAT_RESISTANT_PILLAR);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.LEPIDOLITE_ORE,
                        ModBlocks.DEEPSLATE_LEPIDOLITE_ORE,
                        ModBlocks.RAW_LITHIUM_BLOCK,
                        ModBlocks.LITHIUM_BLOCK,
                        ModBlocks.HEAT_RESISTANT_BRICKS,
                        ModBlocks.HEAT_RESISTANT_PILLAR,
                        ModBlocks.FOUNDRY_FURNACE_CONTROLLER,
                        ModBlocks.COBALT_ORE,
                        ModBlocks.DEEPSLATE_COBALT_ORE,
                        ModBlocks.RAW_COBALT_BLOCK,
                        ModBlocks.COBALT_BLOCK,
                        ModBlocks.COBALT_GRATE,
                        ModBlocks.AIR_INTAKE,
                        ModBlocks.COBALT_BLAST_FURNACE,
                        ModBlocks.DEEPSLATE_ALBITE_ORE,
                        ModBlocks.DEEPSLATE_TUNGSTEN_ORE
                        );

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.HEAT_RESISTANT_BRICKS,
                        ModBlocks.HEAT_RESISTANT_PILLAR,
                        ModBlocks.FOUNDRY_FURNACE_CONTROLLER);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE,
                        ModBlocks.LEPIDOLITE_ORE,
                        ModBlocks.RAW_LITHIUM_BLOCK,
                        ModBlocks.LITHIUM_BLOCK,
                        ModBlocks.COBALT_GRATE,
                        ModBlocks.RAW_COBALT_BLOCK,
                        ModBlocks.COBALT_BLOCK,
                        ModBlocks.AIR_INTAKE,
                        ModBlocks.COBALT_BLAST_FURNACE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.COBALT_ORE,
                        ModBlocks.DEEPSLATE_COBALT_ORE,
                        ModBlocks.DEEPSLATE_TUNGSTEN_ORE,
                        ModBlocks.DEEPSLATE_ALBITE_ORE);

        //getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
                //.add(ModBlocks.DEEPSLATE_ALBITE_ORE);

    }
}
