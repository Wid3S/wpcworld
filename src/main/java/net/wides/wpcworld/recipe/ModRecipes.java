package net.wides.wpcworld.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.WPCWorld;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(WPCWorld.MOD_ID, FoundrySmeltingRecipe.Serializer.ID),
                FoundrySmeltingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(WPCWorld.MOD_ID, FoundrySmeltingRecipe.Type.ID),
                FoundrySmeltingRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(WPCWorld.MOD_ID, CobaltBlastingRecipe.Serializer.ID),
                CobaltBlastingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(WPCWorld.MOD_ID, CobaltBlastingRecipe.Type.ID),
                CobaltBlastingRecipe.Type.INSTANCE);
    }
}
