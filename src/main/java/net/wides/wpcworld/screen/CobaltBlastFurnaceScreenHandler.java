package net.wides.wpcworld.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.wides.wpcworld.block.entity.CobaltBlastFurnaceBlockEntity;
import net.wides.wpcworld.block.entity.FoundryFurnaceControllerBlockEntity;

public class CobaltBlastFurnaceScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final net.minecraft.screen.PropertyDelegate propertyDelegate;

    public CobaltBlastFurnaceScreenHandler(int syncId, PlayerInventory inv, PacketByteBuf buf) {
        this(syncId, inv, (CobaltBlastFurnaceBlockEntity) inv.player.getWorld().getBlockEntity(buf.readBlockPos()));
    }

    public CobaltBlastFurnaceScreenHandler(int syncId, PlayerInventory playerInv, CobaltBlastFurnaceBlockEntity be) {
        super(ModScreenHandlers.COBALT_BLAST_FURNACE_SCREEN_HANDLER, syncId);
        this.inventory = be;
        this.propertyDelegate = be.getPropertyDelegate();
        checkSize(inventory, 3);
        addProperties(propertyDelegate);
        addSlot(new Slot(inventory, 0, 30, 31));
        addSlot(new Slot(inventory, 1, 57, 31));
        addSlot(new Slot(inventory, 2, 104, 31) {

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                super.onTakeItem(player, stack);

                if (inventory instanceof CobaltBlastFurnaceBlockEntity be) {
                    be.dropExperience(player);
                }
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        for (int j = 0; j < 9; j++)
            addSlot(new Slot(playerInv, j, 8 + j * 18, 142));
    }

    @Override public ItemStack quickMove(PlayerEntity player, int slot) { return ItemStack.EMPTY; }
    @Override public boolean canUse(PlayerEntity player) { return inventory.canPlayerUse(player); }
    public int getProgress() { return propertyDelegate.get(0); }
    public int getMaxProgress() { return propertyDelegate.get(1); }
    public int getFuel() { return propertyDelegate.get(2); }
    public int getMaxFuel() { return propertyDelegate.get(3); }
    public int getHeat() { return propertyDelegate.get(4); }
    public int getMaxHeat() { return propertyDelegate.get(5); }
}
