package net.wides.wpcworld.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SeaSicknessEffect extends StatusEffect {

    public SeaSicknessEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().isClient) return;

        boolean inWater = entity.isTouchingWater();

        if (inWater && entity.age % 40 == 0) {
            if (entity.getHealth() < entity.getMaxHealth()) {
                entity.heal(1.0F + amplifier);
            }
        }

        if (inWater) {
            entity.setAir(entity.getMaxAir());
        }

    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}