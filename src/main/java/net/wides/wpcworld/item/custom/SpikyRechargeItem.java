package net.wides.wpcworld.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wides.wpcworld.effect.ModEffects;

public class SpikyRechargeItem extends Item {

    public SpikyRechargeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player) {
            if (player.getMainHandStack().getItem() == this || player.getOffHandStack().getItem() == this) {
                player.addStatusEffect(new StatusEffectInstance(ModEffects.RECHARGE, 30, 0, true, false));
            }
        }
    }
}