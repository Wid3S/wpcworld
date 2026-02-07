package net.wides.wpcworld.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.util.ModStatus;
import net.wides.wpcworld.effect.ModEffects;

public class ModFoodComponents {
    public static final FoodComponent DUALBLOOM_POWDER = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.5F)
            .statusEffect(new StatusEffectInstance(ModEffects.OVERLOAD, 300, 0), 1.0F)
            .alwaysEdible()
            .build();

    public static final FoodComponent DIAMOND_CARROT = new FoodComponent.Builder()
            .hunger(9)
            .saturationModifier(1.2F)
            .build();
}

