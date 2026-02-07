package net.wides.wpcworld.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;

public class FoundryFurnaceControllerScreen
        extends HandledScreen<FoundryFurnaceControllerScreenHandler> {

    private static final Identifier TEXTURE =
            new Identifier(WPCWorld.MOD_ID, "textures/gui/foundry_furnace_controller_gui.png");

    private static final int SMELT_GUI_X = 77;
    private static final int SMELT_GUI_Y = 42;

    private static final int SMELT_TEXTURE_LEFT = 176;
    private static final int SMELT_TEXTURE_RIGHT = 197;
    private static final int SMELT_TEXTURE_TOP = 77;
    private static final int SMELT_TEXTURE_BOTTOM = 90;


    private static final int FUEL_GUI_X = 30;
    private static final int FUEL_GUI_Y = 57;

    private static final int FUEL_TEXTURE_LEFT = 176;
    private static final int FUEL_TEXTURE_RIGHT = 190;
    private static final int FUEL_TEXTURE_TOP = -1;
    private static final int FUEL_TEXTURE_BOTTOM = 13;


    private static final int HEAT_GUI_X = 8;
    private static final int HEAT_GUI_Y = 17;

    private static final int HEAT_TEXTURE_LEFT = 176;
    private static final int HEAT_TEXTURE_RIGHT = 192;
    private static final int HEAT_TEXTURE_TOP = 18;
    private static final int HEAT_TEXTURE_BOTTOM = 75;

    public FoundryFurnaceControllerScreen(
            FoundryFurnaceControllerScreenHandler handler,
            PlayerInventory inventory,
            Text title
    ) {
        super(handler, inventory, title);
        backgroundWidth = 176;
        backgroundHeight = 176;
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2 - 36;
        titleY = -3;;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2 - 9;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        drawSmeltingBar(context, x, y);
        drawFuelBar(context, x, y);
        drawHeatBar(context, x, y);
    }

    private void drawSmeltingBar(DrawContext context, int guiX, int guiY) {
        int progress = handler.getProgress();
        int max = handler.getMaxProgress();
        if (max <= 0) return;

        int totalWidth = SMELT_TEXTURE_RIGHT - SMELT_TEXTURE_LEFT;
        int filled = progress * totalWidth / max;

        context.drawTexture(
                TEXTURE,
                guiX + SMELT_GUI_X,
                guiY + SMELT_GUI_Y,
                SMELT_TEXTURE_LEFT,
                SMELT_TEXTURE_TOP,
                filled,
                SMELT_TEXTURE_BOTTOM - SMELT_TEXTURE_TOP
        );
    }

    private void drawFuelBar(DrawContext context, int guiX, int guiY) {
        int fuel = handler.getFuel();
        int maxFuel = handler.getMaxFuel();
        if (maxFuel <= 0 || fuel <= 0) return;

        int totalHeight = FUEL_TEXTURE_BOTTOM - FUEL_TEXTURE_TOP;
        int filled = fuel * totalHeight / maxFuel;

        context.drawTexture(
                TEXTURE,
                guiX + FUEL_GUI_X,
                guiY + FUEL_GUI_Y + (totalHeight - filled),
                FUEL_TEXTURE_LEFT,
                FUEL_TEXTURE_BOTTOM - filled,
                FUEL_TEXTURE_RIGHT - FUEL_TEXTURE_LEFT,
                filled
        );
    }

    private void drawHeatBar(DrawContext context, int guiX, int guiY) {
        int heat = handler.getHeat();
        int maxHeat = handler.getMaxHeat();
        if (maxHeat <= 0 || heat <= 0) return;

        int totalHeight = HEAT_TEXTURE_BOTTOM - HEAT_TEXTURE_TOP;
        int filled = heat * totalHeight / maxHeat;

        context.drawTexture(
                TEXTURE,
                guiX + HEAT_GUI_X,
                guiY + HEAT_GUI_Y + (totalHeight - filled),
                HEAT_TEXTURE_LEFT,
                HEAT_TEXTURE_BOTTOM - filled,
                HEAT_TEXTURE_RIGHT - HEAT_TEXTURE_LEFT,
                filled
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
