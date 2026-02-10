package net.wides.wpcworld.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;

public class AssemblyTableScreen extends HandledScreen<AssemblyTableScreenHandler> {

    private static final Identifier TEXTURE =
            new Identifier(WPCWorld.MOD_ID, "textures/gui/assembly_table_gui.png");

    private static final int ENERGY_GUI_X = 148;
    private static final int ENERGY_GUI_Y = 34;

    private static final int ENERGY_TEXTURE_LEFT = 176;
    private static final int ENERGY_TEXTURE_RIGHT = 192;
    private static final int ENERGY_TEXTURE_TOP = 0;
    private static final int ENERGY_TEXTURE_BOTTOM = 57;

    public AssemblyTableScreen(
            AssemblyTableScreenHandler handler,
            PlayerInventory inventory,
            Text title
    ) {
        super(handler, inventory, title);
        backgroundWidth = 176;
        backgroundHeight = 222;
    }

    @Override
    protected void init() {
        super.init();
        titleX = 8;
        titleY = 6;
    }


    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        drawEnergyBar(context, x, y);
    }

    private void drawEnergyBar(DrawContext context, int guiX, int guiY) {
        int energy = handler.getCurrentEnergy();
        int maxEnergy = handler.getMaxEnergy();
        if (maxEnergy <= 0 || energy <= 0) return;

        int totalHeight = ENERGY_TEXTURE_BOTTOM - ENERGY_TEXTURE_TOP;
        int filled = energy * totalHeight / maxEnergy;

        context.drawTexture(
                TEXTURE,
                guiX + ENERGY_GUI_X,
                guiY + ENERGY_GUI_Y + (totalHeight - filled),
                ENERGY_TEXTURE_LEFT,
                ENERGY_TEXTURE_BOTTOM - filled,
                ENERGY_TEXTURE_RIGHT - ENERGY_TEXTURE_LEFT,
                filled
        );
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(textRenderer, title, titleX, titleY, 4210752, false);
        context.drawText(textRenderer, playerInventoryTitle, 8, backgroundHeight - 112, 4210752, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}