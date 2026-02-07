package net.wides.wpcworld.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.wides.wpcworld.item.ModItems;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
    }

    private static void registerFuels() {
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.COKE, 4800);
    }
}
