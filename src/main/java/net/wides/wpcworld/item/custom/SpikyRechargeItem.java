package net.wides.wpcworld.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wides.wpcworld.effect.ModEffects;

import java.util.UUID;

public class SpikyRechargeItem extends Item {

    private static final UUID ATTACK_DAMAGE_UUID =
            UUID.fromString("b7d6f3c1-4c2d-4d4e-9d6c-1f5b1f9d7e01");

    public SpikyRechargeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player) {
            EntityAttributeInstance attackDamage =
                    player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);


            if (player.getMainHandStack().getItem() == this || player.getOffHandStack().getItem() == this) {
                if (attackDamage.getModifier(ATTACK_DAMAGE_UUID) == null) {
                    attackDamage.addTemporaryModifier(
                            new EntityAttributeModifier(
                                    ATTACK_DAMAGE_UUID,
                                    "Spiky strength",
                                    3.0D,
                                    EntityAttributeModifier.Operation.ADDITION
                            )
                    );
                }

                player.addStatusEffect(new StatusEffectInstance(ModEffects.RECHARGE, 30, 0, true, false));
            } else {
                attackDamage.removeModifier(ATTACK_DAMAGE_UUID);
            }
        }
    }
}