package net.wides.wpcworld.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.wides.wpcworld.item.ModItems;
import net.wides.wpcworld.recipe.AssemblingRecipe;
import net.wides.wpcworld.screen.AssemblyTableScreenHandler;

import java.util.concurrent.atomic.AtomicBoolean;

public class AssemblyTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, Inventory {
    public static final int GRID_WIDTH = 5;
    public static final int GRID_HEIGHT = 5;
    public static final int GRID_SIZE = GRID_WIDTH * GRID_HEIGHT;

    public static final int FUEL_SLOT = GRID_SIZE;
    public static final int OUTPUT_SLOT = GRID_SIZE + 1;
    public static final int TOTAL_SLOTS = GRID_SIZE + 2;

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(TOTAL_SLOTS, ItemStack.EMPTY);
    private int currentEnergy = 0;
    private int maxEnergy = 1000;
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            if (index == 0) return currentEnergy;
            if (index == 1) return maxEnergy;
            return 0;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) currentEnergy = value;
            if (index == 1) maxEnergy = value;
        }

        @Override
        public int size() {
            return 2;
        }
    };

    protected AssemblyTableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    public AssemblyTableBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.ASSEMBLY_TABLE_BE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, AssemblyTableBlockEntity be) {
        if (world.isClient) return;

        AtomicBoolean dirty = new AtomicBoolean(false);

        ItemStack fuel = be.getStack(FUEL_SLOT);
        if (fuel.isOf(ModItems.DUAL_ENERGY_CORE) && be.currentEnergy < be.maxEnergy) {
            fuel.decrement(1);
            be.currentEnergy = Math.min(be.currentEnergy + 20, be.maxEnergy);
            dirty.set(true);
        }

        Inventory craftingInv = be.getCraftingInventory();
        world.getRecipeManager()
                .getFirstMatch(AssemblingRecipe.Type.INSTANCE, craftingInv, world)
                .ifPresentOrElse(recipe -> {
                    if (be.currentEnergy < recipe.getEnergyCost()) {
                        if (!be.getStack(OUTPUT_SLOT).isEmpty()) {
                            be.setStack(OUTPUT_SLOT, ItemStack.EMPTY);
                            dirty.set(true);
                        }
                        return;
                    }
                    ItemStack preview = recipe.getOutput(world.getRegistryManager());
                    if (!ItemStack.areEqual(be.getStack(OUTPUT_SLOT), preview)) {
                        be.setStack(OUTPUT_SLOT, preview.copy());
                        dirty.set(true);
                    }
                }, () -> {
                    if (!be.getStack(OUTPUT_SLOT).isEmpty()) {
                        be.setStack(OUTPUT_SLOT, ItemStack.EMPTY);
                        dirty.set(true);
                    }
                });

        if (dirty.get()) {
            be.markDirty();
        }
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int max) {
        if (max < 0) max = 0;
        if (max > 1000000) max = 1000000;
        this.maxEnergy = max;
        if (currentEnergy > maxEnergy) currentEnergy = maxEnergy;
        markDirty();
    }

    public void consumeEnergy(int amount) {
        currentEnergy = Math.max(0, currentEnergy - amount);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("station_name.wpcworld.assembly_table");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AssemblyTableScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public int size() {
        return TOTAL_SLOTS;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack s : items) if (!s.isEmpty()) return false;
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(items, slot, amount);
        if (!result.isEmpty()) markDirty();
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(items, slot);
        if (!result.isEmpty()) markDirty();
        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        items.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) stack.setCount(getMaxCountPerStack());
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (world == null) return false;
        if (world.getBlockEntity(pos) != this) return false;
        return player.squaredDistanceTo((double)pos.getX()+0.5, (double)pos.getY()+0.5, (double)pos.getZ()+0.5) <= 64.0;
    }

    @Override
    public void clear() {
        items.clear();
        markDirty();
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
        currentEnergy = nbt.getInt("CurrentEnergy");
        maxEnergy = nbt.getInt("MaxEnergy");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, items);
        nbt.putInt("CurrentEnergy", currentEnergy);
        nbt.putInt("MaxEnergy", maxEnergy);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public Inventory getCraftingInventory() {
        return new Inventory() {
            @Override
            public int size() {
                return GRID_SIZE;
            }

            @Override
            public boolean isEmpty() {
                for (int i = 0; i < GRID_SIZE; i++) {
                    if (!items.get(i).isEmpty()) return false;
                }
                return true;
            }

            @Override
            public ItemStack getStack(int slot) {
                return items.get(slot);
            }

            @Override
            public ItemStack removeStack(int slot, int amount) {
                return ItemStack.EMPTY;
            }

            @Override
            public ItemStack removeStack(int slot) {
                return ItemStack.EMPTY;
            }

            @Override
            public void setStack(int slot, ItemStack stack) {
            }

            @Override
            public boolean canPlayerUse(PlayerEntity player) {
                return true;
            }

            @Override
            public void clear() {
            }

            @Override
            public void markDirty() {
            }
        };
    }
}