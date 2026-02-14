package net.wides.wpcworld.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.item.ModItems;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class AssemblingCategory implements DisplayCategory<AssemblingDisplay> {
    public static final Identifier TEXTURE = new Identifier(WPCWorld.MOD_ID, "textures/gui/assembly_table_recipe_gui.png");
    public static final CategoryIdentifier<AssemblingDisplay> ASSEMBLING = CategoryIdentifier.of(WPCWorld.MOD_ID, "assembling");

    @Override
    public CategoryIdentifier<? extends AssemblingDisplay> getCategoryIdentifier() {
        return ASSEMBLING;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("station_name.wpcworld.assembly_table");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.ASSEMBLY_TABLE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(AssemblingDisplay display, me.shedaniel.math.Rectangle bounds) {
        me.shedaniel.math.Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 55);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new me.shedaniel.math.Rectangle(startPoint.x, startPoint.y, 176, 116)));

        int index = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if(index < display.getInputEntries().size()) {
                    widgets.add(Widgets.createSlot(new me.shedaniel.math.Point(startPoint.x + 12 + col * 18, startPoint.y + 12 + row * 18))
                            .entries(display.getInputEntries().get(index)));
                }
                index++;
            }
        }

        widgets.add(Widgets.createSlot(new me.shedaniel.math.Point(startPoint.x + 106, startPoint.y + 48))
                .entries(display.getOutputEntries().get(0))
                .markOutput()
                .disableBackground());

        List<EntryIngredient> ghostEntries = display.getGhostEntries();
        if (!ghostEntries.isEmpty()) {
            int energy = display.getEnergyCost();
            int count = Math.max(1, energy / 20);
            ItemStack energy_core = new ItemStack(ModItems.DUAL_ENERGY_CORE, count);

            widgets.add(Widgets.createSlot(new Point(startPoint.x + 128, startPoint.y + 48))
                    .entries(List.of(EntryStacks.of(energy_core)))
                    .disableBackground());
        }

        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
            int barHeight = 57;
            int barWidth = 16;
            int energy = display.getEnergyCost();
            int filled = Math.min(energy * barHeight / 1000, barHeight);

            int barX = startPoint.x + 148;
            int barY = startPoint.y + 28;

            graphics.drawTexture(TEXTURE,
                    barX,
                    barY + (barHeight - filled),
                    176,
                    (barHeight - filled),
                    barWidth,
                    filled
            );
        }));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 109;
    }
}