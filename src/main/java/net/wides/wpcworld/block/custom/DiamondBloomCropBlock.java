package net.wides.wpcworld.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.wides.wpcworld.item.ModItems;

public class DiamondBloomCropBlock extends CropBlock {
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = IntProperty.of("age", 0, 4);

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(4, 0, 4, 12, 5, 12),
            Block.createCuboidShape(2, 0, 2, 14, 6, 14),
            Block.createCuboidShape(1, 0, 2, 16, 9, 16),
            Block.createCuboidShape(1, 0, 2, 16, 13, 16),
            Block.createCuboidShape(1, 0, 1, 16, 16, 16)
    };

    public DiamondBloomCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.DIAMONDBLOOM_SEEDS;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public VoxelShape getOutlineShape(
            BlockState state,
            BlockView world,
            BlockPos pos,
            ShapeContext context
    ) {
        return AGE_TO_SHAPE[state.get(getAgeProperty())];
    }
}
