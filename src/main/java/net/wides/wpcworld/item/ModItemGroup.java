package net.wides.wpcworld.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;

import static net.wides.wpcworld.block.ModBlocks.*;
import static net.wides.wpcworld.item.ModItems.*;

public class ModItemGroup {
    public static final ItemGroup WPC_WORLD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(WPCWorld.MOD_ID, "wpc_world_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.wpc_world_group"))
                    .icon(() -> new ItemStack(LITHIUM_INGOT)).entries((displayContext, entries) -> {


                        entries.add(SEA_TYRAN_BOOTS);
                        entries.add(SEA_TYRAN_LEGGINGS);
                        entries.add(SEA_TYRAN_CHESTPLATE);
                        entries.add(SEA_TYRAN_HELMET);


                        entries.add(LITHIUM_POWDER);
                        entries.add(RAW_LITHIUM);
                        entries.add(LEPIDOLITE_ORE);
                        entries.add(DEEPSLATE_LEPIDOLITE_ORE);
                        entries.add(RAW_LITHIUM_BLOCK);
                        entries.add(LITHIUM_NUGGET);
                        entries.add(LITHIUM_INGOT);
                        entries.add(LITHIUM_BLOCK);

                        entries.add(LITHIUM_HOE);
                        entries.add(LITHIUM_SHOVEL);
                        entries.add(LITHIUM_PICKAXE);
                        entries.add(LITHIUM_AXE);
                        entries.add(LITHIUM_SWORD);

                        entries.add(HEAT_RESISTANT_BRICK);
                        entries.add(HEAT_RESISTANT_BRICKS);
                        entries.add(HEAT_RESISTANT_PILLAR);
                        entries.add(FOUNDRY_FURNACE_CONTROLLER);

                        entries.add(RAW_COBALT);
                        entries.add(COBALT_ORE);
                        entries.add(DEEPSLATE_COBALT_ORE);
                        entries.add(RAW_COBALT_BLOCK);
                        entries.add(COBALT_NUGGET);
                        entries.add(COBALT_INGOT);
                        entries.add(COBALT_BLOCK);
                        entries.add(COBALT_GRATE);

                        entries.add(COBALT_HOE);
                        entries.add(COBALT_SHOVEL);
                        entries.add(COBALT_PICKAXE);
                        entries.add(COBALT_AXE);
                        entries.add(COBALT_SWORD);

                        entries.add(COKE);
                        entries.add(AIR_INTAKE);
                        entries.add(COBALT_BLAST_FURNACE);

                        entries.add(RESONANT_SEEDS);

                        entries.add(DIAMONDBLOOM_SEEDS);
                        entries.add(DIAMOND_SHARD);
                        entries.add(COMPRESSED_DIAMOND);
                        entries.add(DIAMOND_PEARL);

                        entries.add(DIAMOND_CARROT);

                        entries.add(DUALBLOOM_SEEDS);
                        entries.add(DUALBLOOM_POWDER);
                        entries.add(DUALBLOOM_LEAF);
                        entries.add(DUAL_ENERGY_CORE);

                        entries.add(ALBITE_CRYSTAL);
                        entries.add(DEEPSLATE_ALBITE_ORE);
                        entries.add(ARTIFACT_BASE);
                        entries.add(ALBITE_STAR);

                        entries.add(SPIKY_RECHARGE);
                        entries.add(PRISM_OF_THE_SEAS);
                        entries.add(CURSED_WORM);
                    }).build());

    public static void registerItemGroups() {
        WPCWorld.LOGGER.info("Registering Item Groups for " + WPCWorld.MOD_ID);
    }
}
