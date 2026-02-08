package net.wides.wpcworld.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.wides.wpcworld.item.custom.TreeCutterItem;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TreeCutterUsageEvent implements PlayerBlockBreakEvents.Before {

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    public static final Set<Block> LOGS = Set.of(
            Blocks.OAK_LOG,
            Blocks.BIRCH_LOG,
            Blocks.SPRUCE_LOG,
            Blocks.JUNGLE_LOG,
            Blocks.ACACIA_LOG,
            Blocks.DARK_OAK_LOG
    );

    public static boolean isLog(Block block) {
        return LOGS.contains(block);
    }

    public static boolean isLeaves(Block block) {
        return block instanceof LeavesBlock;
    }

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos,
                                    BlockState state, @Nullable BlockEntity blockEntity) {

        if (world.isClient) return true;

        ItemStack mainHand = player.getMainHandStack();
        if (!(mainHand.getItem() instanceof TreeCutterItem)) return true;
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return true;
        if (!isLog(state.getBlock())) return true;

        Set<BlockPos> treeBlocks = new HashSet<>();
        Queue<BlockPos> toCheck = new LinkedList<>();
        toCheck.add(pos);
        boolean hasLeaves = false;

        while (!toCheck.isEmpty()) {
            BlockPos current = toCheck.poll();
            if (treeBlocks.contains(current)) continue;

            BlockState currentState = world.getBlockState(current);
            Block currentBlock = currentState.getBlock();

            if (isLog(currentBlock)) {
                treeBlocks.add(current);

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = 0; dy <= 6; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            BlockPos neighbor = current.add(dx, dy, dz);
                            if (!treeBlocks.contains(neighbor)) {
                                toCheck.add(neighbor);
                            }
                        }
                    }
                }
            } else if (isLeaves(currentBlock)) {
                hasLeaves = true;
            }
        }

        if (!hasLeaves) return true;

        for (BlockPos b : treeBlocks) {
            if (HARVESTED_BLOCKS.contains(b)) continue;
            HARVESTED_BLOCKS.add(b);
            serverPlayer.interactionManager.tryBreakBlock(b);
            HARVESTED_BLOCKS.remove(b);
        }

        return true;
    }
}