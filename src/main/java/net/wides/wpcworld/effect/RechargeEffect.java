package net.wides.wpcworld.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

import java.util.UUID;

public class RechargeEffect extends StatusEffect {

    protected RechargeEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.age % 40 == 0) {
            if (entity.getHealth() < entity.getMaxHealth()) {
                entity.heal(1.0F + amplifier);
            }
        }
    }
}