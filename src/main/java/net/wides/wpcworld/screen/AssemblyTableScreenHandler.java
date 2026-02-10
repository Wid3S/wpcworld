package net.wides.wpcworld.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.wides.wpcworld.block.entity.AssemblyTableBlockEntity;
import net.wides.wpcworld.block.entity.CobaltBlastFurnaceBlockEntity;
import net.wides.wpcworld.item.ModItems;

public class AssemblyTableScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final net.minecraft.screen.PropertyDelegate propertyDelegate;

    public AssemblyTableScreenHandler(int syncId, PlayerInventory inv, PacketByteBuf buf) {
        this(syncId, inv, (AssemblyTableBlockEntity) inv.player.getWorld().getBlockEntity(buf.readBlockPos()));
    }

    public AssemblyTableScreenHandler(int syncId, PlayerInventory playerInv, AssemblyTableBlockEntity be) {
        super(ModScreenHandlers.ASSEMBLY_TABLE_SCREEN_HANDLER, syncId);
        this.inventory = be;
        this.propertyDelegate = be.getPropertyDelegate();

        checkSize(inventory, AssemblyTableBlockEntity.TOTAL_SLOTS);
        addProperties(propertyDelegate);

        int startX = 12;
        int startY = 18;
        int slotSize = 18;

        for (int y = 0; y < AssemblyTableBlockEntity.GRID_HEIGHT; y++) {
            for (int x = 0; x < AssemblyTableBlockEntity.GRID_WIDTH; x++) {
                int index = x + y * AssemblyTableBlockEntity.GRID_WIDTH;
                addSlot(new Slot(
                        inventory,
                        index,
                        startX + x * 18,
                        startY + y * 18
                ));
            }
        }

        addSlot(new Slot(
                inventory,
                AssemblyTableBlockEntity.FUEL_SLOT,
                128,
                54
        ) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.DUAL_ENERGY_CORE);
            }
        });

        addSlot(new Slot(
                inventory,
                AssemblyTableBlockEntity.OUTPUT_SLOT,
                106,
                54
        ) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                if (player.getWorld().isClient) {
                    super.onTakeItem(player, stack);
                    return;
                }

                AssemblyTableBlockEntity be = (AssemblyTableBlockEntity) inventory;
                Inventory craftingInv = be.getCraftingInventory();

                player.getWorld()
                        .getRecipeManager()
                        .getFirstMatch(
                                net.wides.wpcworld.recipe.AssemblingRecipe.Type.INSTANCE,
                                craftingInv,
                                player.getWorld()
                        )
                        .ifPresent(recipe -> {

                            if (be.getCurrentEnergy() < recipe.getEnergyCost()) return;

                            be.consumeEnergy(recipe.getEnergyCost());

                            for (int i = 0; i < AssemblyTableBlockEntity.GRID_SIZE; i++) {
                                ItemStack in = be.getStack(i);
                                if (!in.isEmpty()) {
                                    in.decrement(1);
                                }
                            }
                        });

                be.markDirty();
                super.onTakeItem(player, stack);
            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(
                        playerInv,
                        j + i * 9 + 9,
                        8 + j * 18,
                        121 + i * 18
                ));
            }
        }

        for (int j = 0; j < 9; j++) {
            addSlot(new Slot(
                    playerInv,
                    j,
                    8 + j * 18,
                    179
            ));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    public int getCurrentEnergy() {
        return propertyDelegate.get(0);
    }

    public int getMaxEnergy() {
        return propertyDelegate.get(1);
    }
}