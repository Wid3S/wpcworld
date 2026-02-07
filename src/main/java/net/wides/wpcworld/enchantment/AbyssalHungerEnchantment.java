package net.wides.wpcworld.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class AbyssalHungerEnchantment extends Enchantment {

    public AbyssalHungerEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        if (user.getWorld().isClient()) {
            return;
        }

        if (!(target instanceof LivingEntity livingTarget)) {
            return;
        }

        float chance = (0.10F * level);

        if (user.getRandom().nextFloat() > chance) return;

        user.heal(0.5F);

        livingTarget.addStatusEffect(
                new StatusEffectInstance(
                        StatusEffects.SLOWNESS,
                        60,
                        level,
                        false,
                        true,
                        true
                )
        );
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}