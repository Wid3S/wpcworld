package net.wides.wpcworld.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.wides.wpcworld.item.custom.MegaShovelItem;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class MegaShovelUsageEvent implements PlayerBlockBreakEvents.Before {
    /*
     * Inspired by CoFHCore AreaEffectEvents
     * https://github.com/CoFH/CoFHCore
     */
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos,
                                    BlockState state, @Nullable BlockEntity blockEntity) {

        if (world.isClient) {
            return true;
        }

        ItemStack mainHandItem = player.getMainHandStack();

        if (!(mainHandItem.getItem() instanceof MegaShovelItem)) {
            return true;
        }

        if (!(player instanceof ServerPlayerEntity serverPlayer)) {
            return true;
        }

        if (!mainHandItem.getOrCreateNbt().getBoolean(MegaShovelItem.MODE_KEY)) {
            return true;
        }

        if (HARVESTED_BLOCKS.contains(pos)) {
            return true;
        }

        for (BlockPos target : MegaShovelItem.getBlocksToBeDestroyed(1, pos, serverPlayer)) {

            if (pos.equals(target)) continue;

            BlockState targetState = world.getBlockState(target);
            if (targetState.isAir()) continue;
            if (!mainHandItem.isSuitableFor(targetState)) continue;

            HARVESTED_BLOCKS.add(target);
            serverPlayer.interactionManager.tryBreakBlock(target);
            HARVESTED_BLOCKS.remove(target);
        }

        return true;
    }
}