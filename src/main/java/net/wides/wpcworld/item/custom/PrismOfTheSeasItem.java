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

public class PrismOfTheSeasItem extends Item {
    public PrismOfTheSeasItem(Settings settings) {
        super(settings);
    }

    private static final UUID WATER_STRENGTH_UUID =
            UUID.fromString("7f9d7c9a-1c2a-4e3f-9b89-1a3b9b6caa01");
    private static final UUID LAND_WEAKNESS_UUID =
            UUID.fromString("7f9d7c9a-1c2a-4e3f-9b89-1a3b9b6caa02");
    private static final UUID LAND_SLOW_UUID =
            UUID.fromString("7f9d7c9a-1c2a-4e3f-9b89-1a3b9b6caa04");
    private static final UUID WATER_ARMOR_UUID =
            UUID.fromString("7f9d7c9a-1c2a-4e3f-9b89-1a3b9b6caa05");
    private static final UUID LAND_ARMOR_PENALTY_UUID =
            UUID.fromString("7f9d7c9a-1c2a-4e3f-9b89-1a3b9b6caa06");

    private static final int WATER_GRACE_TICKS = 20;

    private int lastWaterTick = -20;

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player) {

            EntityAttributeInstance attackDamage =
                    player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            EntityAttributeInstance movementSpeed =
                    player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            EntityAttributeInstance armor =
                    player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);

            boolean holding = player.getMainHandStack().getItem() == this || player.getOffHandStack().getItem() == this;

            boolean touchingWater = entity.isTouchingWater(); if (touchingWater) {
                lastWaterTick = entity.age;
            }
            boolean inWater = entity.age - lastWaterTick <= WATER_GRACE_TICKS;

            if (holding) {
                player.addStatusEffect(new StatusEffectInstance(ModEffects.SEA_SICKNESS, 30, 0, true, false));

                if (inWater) {
                    if (attackDamage.getModifier(WATER_STRENGTH_UUID) == null) {
                        attackDamage.addTemporaryModifier(
                                new EntityAttributeModifier(
                                        WATER_STRENGTH_UUID,
                                        "Sea strength",
                                        3.0D,
                                        EntityAttributeModifier.Operation.ADDITION
                                )
                        );
                    }
                    if (armor.getModifier(WATER_ARMOR_UUID) == null) {
                        armor.addTemporaryModifier(
                                new EntityAttributeModifier(
                                        WATER_ARMOR_UUID,
                                        "Water armor bonus",
                                        6.0D,
                                        EntityAttributeModifier.Operation.ADDITION
                                )
                        );
                    }

                    armor.removeModifier(LAND_ARMOR_PENALTY_UUID);
                    movementSpeed.removeModifier(LAND_SLOW_UUID);
                    attackDamage.removeModifier(LAND_WEAKNESS_UUID);
                } else {
                    if (attackDamage.getModifier(LAND_WEAKNESS_UUID) == null) {
                        attackDamage.addTemporaryModifier(
                                new EntityAttributeModifier(
                                        LAND_WEAKNESS_UUID,
                                        "Land weakness",
                                        -2.0D,
                                        EntityAttributeModifier.Operation.ADDITION
                                )
                        );
                    }
                    if (armor.getModifier(LAND_ARMOR_PENALTY_UUID) == null) {
                        armor.addTemporaryModifier(
                                new EntityAttributeModifier(
                                        LAND_ARMOR_PENALTY_UUID,
                                        "Land armor penalty",
                                        -4.0D,
                                        EntityAttributeModifier.Operation.ADDITION
                                )
                        );
                    }
                    if (movementSpeed.getModifier(LAND_SLOW_UUID) == null) {
                        movementSpeed.addTemporaryModifier(
                                new EntityAttributeModifier(
                                        LAND_SLOW_UUID,
                                        "Land slow",
                                        -0.3D,
                                        EntityAttributeModifier.Operation.MULTIPLY_TOTAL
                                )
                        );
                    }
                    armor.removeModifier(WATER_ARMOR_UUID);
                    attackDamage.removeModifier(WATER_STRENGTH_UUID);
                }
            } else {
                armor.removeModifier(LAND_ARMOR_PENALTY_UUID);
                movementSpeed.removeModifier(LAND_SLOW_UUID);
                attackDamage.removeModifier(LAND_WEAKNESS_UUID);
                armor.removeModifier(WATER_ARMOR_UUID);
                attackDamage.removeModifier(WATER_STRENGTH_UUID);
            }
        }
    }
}
