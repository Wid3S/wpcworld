package net.wides.wpcworld.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.block.custom.CobaltBlastFurnaceBlock;
import net.wides.wpcworld.block.custom.FoundryFurnaceControllerBlock;
import net.wides.wpcworld.recipe.CobaltBlastingRecipe;
import net.wides.wpcworld.recipe.FoundrySmeltingRecipe;
import net.wides.wpcworld.screen.CobaltBlastFurnaceScreenHandler;
import net.wides.wpcworld.screen.FoundryFurnaceControllerScreenHandler;
import org.jetbrains.annotations.Nullable;

import static net.wides.wpcworld.util.ModTags.Blocks.HEAT_RESISTANT_BLOCKS;

public class CobaltBlastFurnaceBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private static final char[][][] STRUCTURE = {
            {
                    {'G','G','G'},
                    {'G','B','G'},
                    {'G','G','G'}
            },
            {
                    {'H','I','H'},
                    {'H','A','H'},
                    {'H','C','H'}
            },
            {
                    {'H','H','H'},
                    {'H','A','H'},
                    {'H','H','H'}
            },
            {
                    {'H','H','H'},
                    {'H','H','H'},
                    {'H','H','H'}
            }
    };

    private static final int FUEL_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    private static final int INVENTORY_SIZE = 3;

    private static final int DEFAULT_MAX_HEAT = 100;
    private static final int HEAT_CHANGE_INTERVAL = 15;
    private static final int HEAT_CHANGE_AMOUNT = 1;

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

    private int progress;
    private int maxProgress;

    private int fuel;
    private int maxFuel;

    private int heat;
    private int maxHeat = DEFAULT_MAX_HEAT;

    private int heatTick;
    private float storedExperience;

    public CobaltBlastFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COBALT_BLAST_FURNACE_BE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("station_name.wpcworld.cobalt_blast_furnace");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CobaltBlastFurnaceScreenHandler(syncId, inv, this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, items);
        nbt.putInt("Progress", progress);
        nbt.putInt("MaxProgress", maxProgress);
        nbt.putInt("Fuel", fuel);
        nbt.putInt("MaxFuel", maxFuel);
        nbt.putInt("Heat", heat);
        nbt.putInt("MaxHeat", maxHeat);
        nbt.putInt("HeatTick", heatTick);
        nbt.putFloat("StoredXp", storedExperience);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
        progress = nbt.getInt("Progress");
        maxProgress = nbt.getInt("MaxProgress");
        fuel = nbt.getInt("Fuel");
        maxFuel = nbt.getInt("MaxFuel");
        heat = nbt.getInt("Heat");
        maxHeat = nbt.getInt("MaxHeat");
        heatTick = nbt.getInt("HeatTick");
        storedExperience = nbt.getFloat("StoredXp");
    }

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                case 2 -> fuel;
                case 3 -> maxFuel;
                case 4 -> heat;
                case 5 -> maxHeat;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> progress = value;
                case 1 -> maxProgress = value;
                case 2 -> fuel = value;
                case 3 -> maxFuel = value;
                case 4 -> heat = value;
                case 5 -> maxHeat = value;
            }
        }

        @Override
        public int size() {
            return 6;
        }
    };

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    public void addExperience(float amount) {
        storedExperience += amount;
    }

    public void dropExperience(PlayerEntity player) {
        if (world == null || world.isClient) return;

        int xp = net.minecraft.util.math.MathHelper.floor(storedExperience);
        storedExperience = 0;

        while (xp > 0) {
            int split = net.minecraft.entity.ExperienceOrbEntity.roundToOrbSize(xp);
            xp -= split;
            world.spawnEntity(new net.minecraft.entity.ExperienceOrbEntity(
                    world,
                    player.getX(),
                    player.getY() + 0.5,
                    player.getZ(),
                    split
            ));
        }
    }

    @Nullable
    private CobaltBlastingRecipe getCurrentRecipe() {
        if (world == null) return null;

        SimpleInventory inv = new SimpleInventory(1);
        inv.setStack(0, getStack(INPUT_SLOT));

        return world.getRecipeManager()
                .getFirstMatch(CobaltBlastingRecipe.Type.INSTANCE, inv, world)
                .orElse(null);
    }

    private boolean canCraft(CobaltBlastingRecipe recipe) {
        ItemStack input = getStack(INPUT_SLOT);
        if (input.getCount() < recipe.getInputCount()) return false;

        ItemStack result = recipe.getOutput(world.getRegistryManager()).copy();
        result.setCount(recipe.getOutputCount());

        ItemStack output = getStack(OUTPUT_SLOT);
        if (output.isEmpty()) return true;
        if (!ItemStack.canCombine(output, result)) return false;

        return output.getCount() + result.getCount() <= output.getMaxCount();
    }

    private void craft(CobaltBlastingRecipe recipe) {
        removeStack(INPUT_SLOT, recipe.getInputCount());

        ItemStack result = recipe.getOutput(world.getRegistryManager()).copy();
        result.setCount(recipe.getOutputCount());

        ItemStack output = getStack(OUTPUT_SLOT);
        if (output.isEmpty()) {
            setStack(OUTPUT_SLOT, result);
        } else {
            output.increment(result.getCount());
        }

        storedExperience += recipe.getExperience();
        markDirty();
    }

    private boolean checkBlock(BlockState state, char symbol) {
        return switch (symbol) {
            case 'G' -> state.isOf(ModBlocks.COBALT_GRATE);
            case 'B' -> state.isOf(ModBlocks.COBALT_BLOCK);
            case 'H' -> state.isIn(HEAT_RESISTANT_BLOCKS);
            case 'I' -> state.isOf(ModBlocks.AIR_INTAKE);
            case 'A' -> state.isAir();
            case 'C' -> state.isOf(ModBlocks.COBALT_BLAST_FURNACE);
            default -> false;
        };
    }


    private String blockIdOrName(BlockState state) {
        try {
            return Registries.BLOCK.getId(state.getBlock()).toString();
        } catch (Exception e) {
            return state.getBlock().toString();
        }
    }

    private BlockPos rotate(BlockPos center, int x, int y, int z, Direction dir) {
        int dx = x - 1;
        int dz = z - 1;

        int wx = center.getX();
        int wy = center.getY() + (y - 1); // 🔥 ВАЖНО
        int wz = center.getZ();

        switch (dir) {
            case NORTH -> { wx += dx; wz += dz; }
            case SOUTH -> { wx -= dx; wz -= dz; }
            case WEST  -> { wx += dz; wz -= dx; }
            case EAST  -> { wx -= dz; wz += dx; }
        }

        return new BlockPos(wx, wy, wz);
    }

    public boolean isMultiblockValid() {
        if (world == null) return false;

        for (Direction dir : Direction.Type.HORIZONTAL) {

            BlockPos center = pos.offset(dir);

            if (!world.getBlockState(center).isAir()) continue;

            if (checkStructureFromCenter(center, dir)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkStructureFromCenter(BlockPos center, Direction dir) {
        if (world == null) return false;

        for (int y = 0; y < STRUCTURE.length; y++) {
            for (int z = 0; z < 3; z++) {
                for (int x = 0; x < 3; x++) {
                    char expected = STRUCTURE[y][z][x];
                    BlockPos p = rotate(center, x, y, z, dir);
                    BlockState state = world.getBlockState(p);

                    boolean ok = checkBlock(state, expected);
                    if (!ok) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private void tryConsumeFuel() {
        if (fuel > 0) return;

        ItemStack stack = getStack(FUEL_SLOT);
        if (stack.isEmpty()) return;

        Integer burn = FuelRegistry.INSTANCE.get(stack.getItem());
        if (burn == null || burn <= 0) return;

        removeStack(FUEL_SLOT, 1);
        fuel = burn;
        maxFuel = burn;
        markDirty();
    }

    public static void tick(World world, BlockPos pos, BlockState state, CobaltBlastFurnaceBlockEntity be) {
        if (world.isClient) return;

        boolean wasBurning = be.fuel > 0;

        if (!wasBurning) {
            be.tryConsumeFuel();
        }

        boolean isBurning = be.fuel > 0;

        if (isBurning) {
            be.fuel--;
        }

        if (wasBurning != isBurning) {
            world.setBlockState(
                    pos,
                    state.with(CobaltBlastFurnaceBlock.LIT, isBurning),
                    3
            );
        }

        if (wasBurning == isBurning) {
            world.setBlockState(
                    pos,
                    state.with(CobaltBlastFurnaceBlock.LIT, isBurning),
                    3
            );
        }

        be.heatTick++;

        if (be.heatTick >= HEAT_CHANGE_INTERVAL) {
            be.heatTick = 0;

            if (isBurning) {
                be.heat = Math.min(be.maxHeat, be.heat + HEAT_CHANGE_AMOUNT);
            } else {
                be.heat = Math.max(0, be.heat - HEAT_CHANGE_AMOUNT);
            }
        }

        CobaltBlastingRecipe recipe = be.getCurrentRecipe();

        if (recipe == null || be.heat < be.maxHeat || !be.canCraft(recipe)) {
            if (be.progress != 0) {
                be.progress = 0;
                be.maxProgress = 0;
                be.markDirty();
            }
            return;
        }

        be.maxProgress = recipe.getCookTime();
        be.progress++;

        if (be.progress >= be.maxProgress) {
            be.progress = 0;
            be.craft(recipe);
            be.markDirty();
        }
    }
}