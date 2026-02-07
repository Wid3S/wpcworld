package net.wides.wpcworld.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

import java.util.UUID;

public class OverloadEffect extends StatusEffect {


    private static final UUID ATTACK_DAMAGE_UUID =
            UUID.fromString("b7d6f3c1-4c2d-4d4e-9d6c-1f5b1f9d7e01");

    private static final UUID SPEED_UUID =
            UUID.fromString("c2a9e3f4-8a1b-4c9a-b6e7-91d5a0f8e102");

    protected OverloadEffect(StatusEffectCategory category, int color) {
        super(category, color);


        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                ATTACK_DAMAGE_UUID.toString(),
                3,
                EntityAttributeModifier.Operation.ADDITION
        );

        this.addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                SPEED_UUID.toString(),
                0.4,
                EntityAttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!(entity.getWorld() instanceof ServerWorld world)) return;

        int level = amplifier + 1;

        float chance = 0.01f + (level * 0.01f);

        if (world.random.nextFloat() < chance) {

            float damage = 1.0f + level * 0.75f;

            entity.damage(
                    world.getDamageSources().magic(),
                    damage
            );
        }
    }
}
