package net.wides.wpcworld.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

import java.util.UUID;

public class RechargeEffect extends StatusEffect {


    private static final UUID ATTACK_DAMAGE_UUID =
            UUID.fromString("b7d6f3c1-4c2d-4d4e-9d6c-1f5b1f9d7e01");

    protected RechargeEffect(StatusEffectCategory category, int color) {
        super(category, color);


        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                ATTACK_DAMAGE_UUID.toString(),
                3,
                EntityAttributeModifier.Operation.ADDITION
        );
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