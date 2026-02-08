package net.wides.wpcworld;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.block.entity.ModBlockEntities;
import net.wides.wpcworld.effect.ModEffects;
import net.wides.wpcworld.enchantment.ModEnchantments;
import net.wides.wpcworld.item.ModItemGroup;
import net.wides.wpcworld.item.ModItems;
import net.wides.wpcworld.recipe.ModRecipes;
import net.wides.wpcworld.screen.ModScreenHandlers;
import net.wides.wpcworld.util.ExcavatorShovelUsageEvent;
import net.wides.wpcworld.util.ExcavatorUsageEvent;
import net.wides.wpcworld.util.ModRegistries;
import net.wides.wpcworld.util.TreeCutterUsageEvent;
import net.wides.wpcworld.world.ModConfiguredFeatures;
import net.wides.wpcworld.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WPCWorld implements ModInitializer {
	public static final String MOD_ID = "wpcworld";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        ModItemGroup.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ModRegistries.registerModStuffs();
        ModEnchantments.registerModEnchantments();

        ModWorldGeneration.generateModWorldGeneration();

        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandler();

        ModRecipes.registerRecipes();

        ModEffects.registerEffects();

        PlayerBlockBreakEvents.BEFORE.register(new ExcavatorShovelUsageEvent());
        PlayerBlockBreakEvents.BEFORE.register(new ExcavatorUsageEvent());
        PlayerBlockBreakEvents.BEFORE.register(new TreeCutterUsageEvent());
	}
}