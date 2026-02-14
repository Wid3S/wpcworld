package net.wides.wpcworld.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.item.custom.*;

public class ModItems {
    public static final Item  RAW_LITHIUM = registerItem("raw_lithium",
            new Item(new FabricItemSettings()));
    public static final Item  LITHIUM_POWDER = registerItem("lithium_powder",
            new Item(new FabricItemSettings()));
    public static final Item  LITHIUM_NUGGET = registerItem("lithium_nugget",
            new Item(new FabricItemSettings()));
    public static final Item  LITHIUM_INGOT = registerItem("lithium_ingot",
            new Item(new FabricItemSettings()));

    public static final Item  LITHIUM_PICKAXE = registerItem("lithium_pickaxe",
            new PickaxeItem(ModToolMaterial.LITHIUM, 1, -2.8F, new FabricItemSettings()));
    public static final Item  LITHIUM_AXE = registerItem("lithium_axe",
            new AxeItem(ModToolMaterial.LITHIUM, 5, -3.1F, new FabricItemSettings()));
    public static final Item  LITHIUM_SHOVEL = registerItem("lithium_shovel",
            new ShovelItem(ModToolMaterial.LITHIUM, 1.5F, -3.0F, new FabricItemSettings()));
    public static final Item  LITHIUM_HOE = registerItem("lithium_hoe",
            new HoeItem(ModToolMaterial.LITHIUM, -2, 0F, new FabricItemSettings()));
    public static final Item  LITHIUM_SWORD = registerItem("lithium_sword",
            new SwordItem(ModToolMaterial.LITHIUM, 3, -2.4F, new FabricItemSettings()));

    public static final Item  LITHIUM_HELMET = registerItem("lithium_helmet",
            new ArmorItem(ModArmorMaterials.LITHIUM, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item  LITHIUM_CHESTPLATE = registerItem("lithium_chestplate",
            new ArmorItem(ModArmorMaterials.LITHIUM, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item  LITHIUM_LEGGINGS = registerItem("lithium_leggings",
            new ArmorItem(ModArmorMaterials.LITHIUM, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item  LITHIUM_BOOTS = registerItem("lithium_boots",
            new ArmorItem(ModArmorMaterials.LITHIUM, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item  HEAT_RESISTANT_BRICK = registerItem("heat_resistant_brick",
            new Item(new FabricItemSettings()));

    public static final Item  RAW_COBALT = registerItem("raw_cobalt",
            new Item(new FabricItemSettings()));
    public static final Item  COBALT_NUGGET = registerItem("cobalt_nugget",
            new Item(new FabricItemSettings()));
    public static final Item  COBALT_INGOT = registerItem("cobalt_ingot",
            new Item(new FabricItemSettings()));

    public static final Item  COBALT_HOE = registerItem("cobalt_hoe",
            new HoeItem(ModToolMaterial.COBALT, -4, 0F, new FabricItemSettings()));
    public static final Item  COBALT_SHOVEL = registerItem("cobalt_shovel",
            new ShovelItem(ModToolMaterial.COBALT, 1.5F, -3.0F, new FabricItemSettings()));
    public static final Item  COBALT_PICKAXE = registerItem("cobalt_pickaxe",
            new PickaxeItem(ModToolMaterial.COBALT, 1, -2.8F, new FabricItemSettings()));
    public static final Item  COBALT_AXE = registerItem("cobalt_axe",
            new AxeItem(ModToolMaterial.COBALT, 5, -3.1F, new FabricItemSettings()));
    public static final Item  COBALT_SWORD = registerItem("cobalt_sword",
            new SwordItem(ModToolMaterial.COBALT, 3, -2.4F, new FabricItemSettings()));

    public static final Item  COBALT_HELMET = registerItem("cobalt_helmet",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item  COBALT_CHESTPLATE = registerItem("cobalt_chestplate",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item  COBALT_LEGGINGS = registerItem("cobalt_leggings",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item  COBALT_BOOTS = registerItem("cobalt_boots",
            new ArmorItem(ModArmorMaterials.COBALT, ArmorItem.Type.BOOTS, new FabricItemSettings()));



    public static final Item  COKE = registerItem("coke",
            new Item(new FabricItemSettings()));

    public static final Item  ALBITE_CRYSTAL = registerItem("albite_crystal",
            new Item(new FabricItemSettings()));
    public static final Item  ALBITE_STAR = registerItem("albite_star",
            new Item(new FabricItemSettings()));
    public static final Item  ARTIFACT_BASE = registerItem("artifact_base",
            new Item(new FabricItemSettings()));

    public static final Item  RAW_TUNGSTEN = registerItem("raw_tungsten",
            new Item(new FabricItemSettings()));
    public static final Item  TUNGSTEN_INGOT = registerItem("tungsten_ingot",
            new Item(new FabricItemSettings()));

    public static final Item  TUNGSTEN_EXCAVATOR = registerItem("tungsten_excavator",
            new ExcavatorItem(ModToolMaterial.TUNGSTEN, 1, -2.8F, new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item  TUNGSTEN_TREECUTTER = registerItem("tungsten_treecutter",
            new TreeCutterItem(ModToolMaterial.TUNGSTEN, 5, -3.1F, new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item  TUNGSTEN_MEGA_SHOVEL = registerItem("tungsten_mega_shovel",
            new MegaShovelItem(ModToolMaterial.TUNGSTEN, 1.5F, -3.0F, new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item  TUNGSTEN_HOE = registerItem("tungsten_hoe",
            new HoeItem(ModToolMaterial.TUNGSTEN, -6, 0F, new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item  TUNGSTEN_SWORD = registerItem("tungsten_sword",
            new SwordItem(ModToolMaterial.TUNGSTEN, 3, -2.4F, new FabricItemSettings().rarity(Rarity.RARE)));

    public static final Item  TUNGSTEN_HELMET = registerItem("tungsten_helmet",
            new ArmorItem(ModArmorMaterials.TUNGSTEN, ArmorItem.Type.HELMET, new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item  TUNGSTEN_CHESTPLATE = registerItem("tungsten_chestplate",
            new ArmorItem(ModArmorMaterials.TUNGSTEN, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item  TUNGSTEN_LEGGINGS = registerItem("tungsten_leggings",
            new ArmorItem(ModArmorMaterials.TUNGSTEN, ArmorItem.Type.LEGGINGS, new FabricItemSettings().rarity(Rarity.RARE)));
    public static final Item  TUNGSTEN_BOOTS = registerItem("tungsten_boots",
            new ArmorItem(ModArmorMaterials.TUNGSTEN, ArmorItem.Type.BOOTS, new FabricItemSettings().rarity(Rarity.RARE)));

    public static final Item  CHROME_INGOT = registerItem("chrome_ingot",
            new Item(new FabricItemSettings().fireproof().rarity(Rarity.EPIC)));


    public static final Item RESONANT_SEEDS = registerItem("resonant_seeds",
            new Item(new FabricItemSettings()));

    public static final Item DIAMONDBLOOM_SEEDS = registerItem("diamondbloom_seeds",
            new AliasedBlockItem(ModBlocks.DIAMONDBLOOM_CROP, new FabricItemSettings()));
    public static final Item DIAMOND_SHARD = registerItem("diamond_shard",
            new Item(new FabricItemSettings()));
    public static final Item COMPRESSED_DIAMOND = registerItem("compressed_diamond",
            new Item(new FabricItemSettings()));
    public static final Item DIAMOND_PEARL = registerItem("diamond_pearl",
            new Item(new FabricItemSettings()));

    public static final Item DIAMOND_CARROT = registerItem("diamond_carrot",
            new Item(new FabricItemSettings().food(ModFoodComponents.DIAMOND_CARROT)));

    public static final Item COMPRESSED_OBSIDIAN = registerItem("compressed_obsidian",
            new Item(new FabricItemSettings()));
    public static final Item OBSIDIAN_STICK = registerItem("obsidian_stick",
            new Item(new FabricItemSettings()));

    public static final Item DUALBLOOM_SEEDS = registerItem("dualbloom_seeds",
            new AliasedBlockItem(ModBlocks.DUALBLOOM_CROP, new FabricItemSettings()));
    public static final Item DUALBLOOM_LEAF = registerItem("dualbloom_leaf",
            new Item(new FabricItemSettings()));
    public static final Item DUALBLOOM_POWDER = registerItem("dualbloom_powder",
            new Item(new FabricItemSettings().food(ModFoodComponents.DUALBLOOM_POWDER)));
    public static final Item DUAL_ENERGY_CORE = registerItem("dual_energy_core",
            new Item(new FabricItemSettings()));

    public static final Item  SPIKY_RECHARGE = registerItem("spiky_recharge",
            new SpikyRechargeItem(new FabricItemSettings().fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item PRISM_OF_THE_SEAS = registerItem("prism_of_the_seas",
            new PrismOfTheSeasItem(new FabricItemSettings().fireproof().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item CURSED_WORM = registerItem("cursed_worm",
            new Item(new FabricItemSettings().fireproof().rarity(Rarity.EPIC).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(WPCWorld.MOD_ID, name), item);
    }

    public static void registerModItems() {
        WPCWorld.LOGGER.info("Registering Mod Items for " + WPCWorld.MOD_ID);
    }
}
