package net.wides.wpcworld.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;

import java.util.LinkedList;
import java.util.List;

public class FoundrySmeltingCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE =
            new Identifier(WPCWorld.MOD_ID, "textures/gui/foundry_furnace_controller_recipe_gui.png");
    public static final CategoryIdentifier<FoundrySmeltingDisplay> FOUNDRY_SMELTING =
            CategoryIdentifier.of(WPCWorld.MOD_ID, "foundry_smelting");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return FOUNDRY_SMELTING;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("station_name.wpcworld.foundry_furnace_controller");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.FOUNDRY_FURNACE_CONTROLLER.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 45);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE,
                new Rectangle(startPoint.x, startPoint.y, 175, 91)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 57, startPoint.y + 40))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 104, startPoint.y + 40))
                .markOutput().entries(display.getOutputEntries().get(0))
                .disableBackground());

        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {

            int x = startPoint.x + 8;
            int y = startPoint.y + 17;

            int u = 176;
            int v = 18;

            int width = 16;
            int height = 57;

            graphics.drawTexture(TEXTURE, x, y, u, v, width, height);
        }));

        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {

            int width = 21;
            int height = 13;

            int progress = (int)((System.currentTimeMillis() / 1280) % width);

            int x = startPoint.x + 77;
            int y = startPoint.y + 42;

            int u = 176;
            int v = 77;

            graphics.drawTexture(TEXTURE, x, y, u, v, progress, height);
        }));

        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {

            int width = 13;
            int height = 13;

            int progress = height - (int)((System.currentTimeMillis() / 1280) % height);

            int x = startPoint.x + 31;
            int y = startPoint.y + 41 + (height - progress);

            int u = 176;
            int v = 0 + (height - progress);

            graphics.drawTexture(TEXTURE, x, y, u, v, width, progress);
        }));


        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
