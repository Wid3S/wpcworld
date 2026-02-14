package net.wides.wpcworld.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.wides.wpcworld.recipe.AssemblingRecipe;

import java.util.ArrayList;
import java.util.List;

public class AssemblingDisplay extends BasicDisplay {

    private final EntryIngredient ghostEntry;
    private final int energy;

    public AssemblingDisplay(AssemblingRecipe recipe, ItemStack ghostItem) {
        super(getInputList(recipe), List.of(EntryIngredient.of(EntryStacks.of(recipe.getOutput(null)))));
        this.ghostEntry = EntryIngredient.of(EntryStacks.of(ghostItem));
        this.energy = recipe.getEnergyCost();
    }

    public AssemblingDisplay(AssemblingRecipe recipe) {
        super(getInputList(recipe), List.of(EntryIngredient.of(EntryStacks.of(recipe.getOutput(null)))));
        this.ghostEntry = EntryIngredient.empty();
        this.energy = recipe.getEnergyCost();
    }

    private static List<EntryIngredient> getInputList(AssemblingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            if (i < recipe.getIngredients().size()) {
                list.add(EntryIngredients.ofIngredient(recipe.getIngredients().get(i)));
            } else {
                list.add(EntryIngredient.empty());
            }
        }
        return list;
    }

    public List<EntryIngredient> getGhostEntries() {
        return List.of(ghostEntry);
    }

    public int getEnergyCost() {
        return energy;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return AssemblingCategory.ASSEMBLING;
    }
}