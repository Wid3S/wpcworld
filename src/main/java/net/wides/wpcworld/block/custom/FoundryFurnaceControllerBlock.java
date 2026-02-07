package net.wides.wpcworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.wides.wpcworld.block.entity.FoundryFurnaceControllerBlockEntity;
import net.wides.wpcworld.block.entity.ModBlockEntities;

public class FoundryFurnaceControllerBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final BooleanProperty LIT = Properties.LIT;

    public FoundryFurnaceControllerBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Properties.HORIZONTAL_FACING.getValues().iterator().next()).with(LIT, false));
    }

    @Override
    public ActionResult onUse(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            BlockHitResult hit
    ) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof FoundryFurnaceControllerBlockEntity controller)) {
            return ActionResult.PASS;
        }

        if (!controller.isMultiblockValid()) {
            player.sendMessage(
                    Text.translatable("message.wpcworld.foundry.invalid_structure"),
                    true
            );
            return ActionResult.CONSUME;
        }

        player.openHandledScreen((FoundryFurnaceControllerBlockEntity) world.getBlockEntity(pos));
        return ActionResult.CONSUME;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FoundryFurnaceControllerBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, ModBlockEntities.FOUNDRY_FURNACE_CONTROLLER_BE, FoundryFurnaceControllerBlockEntity::tick);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos,
                                BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof FoundryFurnaceControllerBlockEntity be) {
                ItemScatterer.spawn(world, pos, be);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!state.get(LIT)) return;

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        if (random.nextDouble() < 0.1) {
            world.playSound(
                    x, y, z,
                    SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE,
                    SoundCategory.BLOCKS,
                    1.0F,
                    1.0F,
                    false
            );
        }

        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();

        double offset = 0.52;
        double randomOffset = random.nextDouble() * 0.6 - 0.3;

        double px = axis == Direction.Axis.X
                ? x + offset * direction.getOffsetX()
                : x + randomOffset;

        double pz = axis == Direction.Axis.Z
                ? z + offset * direction.getOffsetZ()
                : z + randomOffset;

        world.addParticle(ParticleTypes.SMOKE, px, y + 0.1, pz, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.FLAME, px, y + 0.1, pz, 0.0, 0.0, 0.0);
    }
}