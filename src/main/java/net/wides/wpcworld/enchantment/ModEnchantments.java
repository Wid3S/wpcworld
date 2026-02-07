package net.wides.wpcworld.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;

public class ModEnchantments {

    public static final Enchantment ABYSSAL_HUNGER = register("abyssal_hunger",
            new AbyssalHungerEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(WPCWorld.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        WPCWorld.LOGGER.info("Registering Mod Enchantments for " + WPCWorld.MOD_ID);
    }
}
