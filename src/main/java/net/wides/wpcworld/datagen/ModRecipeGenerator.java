package net.wides.wpcworld.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.wides.wpcworld.block.ModBlocks;
import net.wides.wpcworld.datagen.recipe.CobaltBlastingRecipeBuilder;
import net.wides.wpcworld.datagen.recipe.FoundrySmeltingRecipeBuilder;
import net.wides.wpcworld.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {

    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {

        // -------- LITHIUM --------

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_POWDER, 3)
                .input(ModItems.RAW_LITHIUM)
                .criterion(hasItem(ModItems.RAW_LITHIUM), conditionsFromItem(ModItems.RAW_LITHIUM))
                .offerTo(consumer, id("lithium_powder_from_raw"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_INGOT, 9)
                .input(ModBlocks.LITHIUM_BLOCK)
                .criterion(hasItem(ModBlocks.LITHIUM_BLOCK), conditionsFromItem(ModBlocks.LITHIUM_BLOCK))
                .offerTo(consumer, id("lithium_ingot_from_block"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LITHIUM_BLOCK)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.LITHIUM_INGOT)
                .criterion(hasItem(ModItems.LITHIUM_INGOT), conditionsFromItem(ModItems.LITHIUM_INGOT))
                .offerTo(consumer, id("lithium_block_from_ingots"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_INGOT)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .input('N', ModItems.LITHIUM_NUGGET)
                .criterion(hasItem(ModItems.LITHIUM_NUGGET), conditionsFromItem(ModItems.LITHIUM_NUGGET))
                .offerTo(consumer, id("lithium_ingot_from_nuggets"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_NUGGET, 9)
                .input(ModItems.LITHIUM_INGOT)
                .criterion(hasItem(ModItems.LITHIUM_INGOT), conditionsFromItem(ModItems.LITHIUM_INGOT))
                .offerTo(consumer, id("lithium_nuggets_from_ingot"));

        // -------- COBALT --------

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_INGOT, 9)
                .input(ModBlocks.COBALT_BLOCK)
                .criterion(hasItem(ModBlocks.COBALT_BLOCK), conditionsFromItem(ModBlocks.COBALT_BLOCK))
                .offerTo(consumer, id("cobalt_ingot_from_block"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COBALT_BLOCK)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .input('I', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer, id("cobalt_block_from_ingots"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_INGOT)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .input('N', ModItems.COBALT_NUGGET)
                .criterion(hasItem(ModItems.COBALT_NUGGET), conditionsFromItem(ModItems.COBALT_NUGGET))
                .offerTo(consumer, id("cobalt_ingot_from_nuggets"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COBALT_GRATE, 4)
                .pattern(" N ")
                .pattern("N N")
                .pattern(" N ")
                .input('N', ModBlocks.COBALT_BLOCK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer, id("cobalt_grate_from_blocks"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_NUGGET, 9)
                .input(ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer, id("cobalt_nuggets_from_ingot"));


        // -------- DUALBLOOM --------

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DUALBLOOM_SEEDS)
                .pattern("DDN")
                .pattern("DAN")
                .pattern("DNN")
                .input('A', ModItems.RESONANT_SEEDS)
                .input('N', Items.MELON_SEEDS)
                .input('D', Items.PUMPKIN_SEEDS)
                .criterion(hasItem(ModItems.RESONANT_SEEDS), conditionsFromItem(ModItems.RESONANT_SEEDS))
                .offerTo(consumer, id("dualbloom_seeds"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DUALBLOOM_POWDER, 2)
                .input(ModItems.DUALBLOOM_LEAF)
                .criterion(hasItem(ModItems.DUALBLOOM_LEAF), conditionsFromItem(ModItems.DUALBLOOM_LEAF))
                .offerTo(consumer, id("dualbloom_powder_from_leaf"));

        // -------- DIAMOND BLOOM --------

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMONDBLOOM_SEEDS)
                .pattern("DDD")
                .pattern("DRD")
                .pattern("DDD")
                .input('R', ModItems.RESONANT_SEEDS)
                .input('D', Items.DIAMOND)
                .criterion(hasItem(ModItems.RESONANT_SEEDS), conditionsFromItem(ModItems.RESONANT_SEEDS))
                .offerTo(consumer, id("diamondbloom_seeds"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.DIAMOND)
                .pattern(" S ")
                .pattern("S S")
                .pattern(" S ")
                .input('S', ModItems.DIAMOND_SHARD)
                .criterion(hasItem(ModItems.DIAMOND_SHARD), conditionsFromItem(ModItems.DIAMOND_SHARD))
                .offerTo(consumer, id("diamond_from_shards"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_SHARD, 4)
                .input(Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(consumer, id("shards_from_diamond"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COMPRESSED_DIAMOND)
                .pattern("DDD")
                .pattern("D D")
                .pattern("DDD")
                .input('D', Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(consumer, id("compressed_diamond"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_PEARL)
                .pattern("CCC")
                .pattern("CPC")
                .pattern("CCC")
                .input('C', ModItems.COMPRESSED_DIAMOND)
                .input('P', Items.ENDER_PEARL)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(consumer, id("diamond_pearl"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIAMOND_CARROT)
                .pattern(" S ")
                .pattern("SCS")
                .pattern(" S ")
                .input('S', ModItems.DIAMOND_SHARD)
                .input('C', Items.CARROT)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(ModItems.DIAMOND_SHARD))
                .offerTo(consumer, id("diamond_carrot"));

        // -------- ALBITE --------

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RESONANT_SEEDS)
                .pattern("XNX")
                .pattern("NAN")
                .pattern("XNX")
                .input('A', ModItems.ALBITE_CRYSTAL)
                .input('X', Items.WHEAT_SEEDS)
                .input('N', ModItems.LITHIUM_INGOT)
                .criterion(hasItem(ModItems.ALBITE_CRYSTAL), conditionsFromItem(ModItems.ALBITE_CRYSTAL))
                .offerTo(consumer, id("resonant_seeds"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ARTIFACT_BASE)
                .pattern(" A ")
                .pattern("ADA")
                .pattern(" A ")
                .input('A', ModItems.ALBITE_CRYSTAL)
                .input('D', ModItems.DIAMOND_PEARL)
                .criterion(hasItem(ModItems.ALBITE_CRYSTAL), conditionsFromItem(ModItems.ALBITE_CRYSTAL))
                .offerTo(consumer, id("artifact_base"));

        // -------- RAW BLOCKS --------

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_LITHIUM_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_LITHIUM)
                .criterion(hasItem(ModItems.RAW_LITHIUM), conditionsFromItem(ModItems.RAW_LITHIUM))
                .offerTo(consumer, id("raw_lithium_block_from_raw"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_LITHIUM, 9)
                .input(ModBlocks.RAW_LITHIUM_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_LITHIUM_BLOCK), conditionsFromItem(ModBlocks.RAW_LITHIUM_BLOCK))
                .offerTo(consumer, id("raw_lithium_from_block"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_COBALT_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_COBALT)
                .criterion(hasItem(ModItems.RAW_COBALT), conditionsFromItem(ModItems.RAW_COBALT))
                .offerTo(consumer, id("raw_cobalt_block_from_raw"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_COBALT, 9)
                .input(ModBlocks.RAW_COBALT_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_COBALT_BLOCK), conditionsFromItem(ModBlocks.RAW_COBALT_BLOCK))
                .offerTo(consumer, id("raw_cobalt_from_block"));

        // -------- HEAT RESISTANT --------

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEAT_RESISTANT_BRICK, 4)
                .pattern("!#!")
                .pattern("#*#")
                .pattern("!#!")
                .input('*', Items.BRICK)
                .input('#', ModItems.LITHIUM_NUGGET)
                .input('!', Items.QUARTZ)
                .criterion(hasItem(ModItems.LITHIUM_NUGGET), conditionsFromItem(ModItems.LITHIUM_NUGGET))
                .offerTo(consumer);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.HEAT_RESISTANT_BRICKS)
                .pattern("##")
                .pattern("##")
                .input('#', ModItems.HEAT_RESISTANT_BRICK)
                .criterion(hasItem(ModItems.HEAT_RESISTANT_BRICK), conditionsFromItem(ModItems.HEAT_RESISTANT_BRICK))
                .offerTo(consumer);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEAT_RESISTANT_BRICK, 4)
                .input(ModBlocks.HEAT_RESISTANT_BRICKS)
                .criterion(hasItem(ModBlocks.HEAT_RESISTANT_BRICKS), conditionsFromItem(ModBlocks.HEAT_RESISTANT_BRICKS))
                .offerTo(consumer, id("heat_resistant_brick_from_bricks"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.HEAT_RESISTANT_PILLAR, 2)
                .pattern("H")
                .pattern("H")
                .input('H', ModBlocks.HEAT_RESISTANT_BRICKS)
                .criterion(hasItem(ModBlocks.HEAT_RESISTANT_BRICKS), conditionsFromItem(ModBlocks.HEAT_RESISTANT_BRICKS))
                .offerTo(consumer);

        // -------- TOOLS --------

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPIKY_RECHARGE)
                .pattern("PXP")
                .pattern("XCX")
                .pattern("PXP")
                .input('C', ModItems.ARTIFACT_BASE)
                .input('X', ModItems.DUALBLOOM_LEAF)
                .input('P', ModItems.DUALBLOOM_POWDER)
                .criterion(hasItem(ModItems.ALBITE_CRYSTAL), conditionsFromItem(ModItems.ALBITE_CRYSTAL))
                .offerTo(consumer, id("spiky_recharge"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PRISM_OF_THE_SEAS)
                .pattern("NPS")
                .pattern("PCP")
                .pattern("SPH")
                .input('C', ModItems.ARTIFACT_BASE)
                .input('N', Items.NAUTILUS_SHELL)
                .input('P', Items.PRISMARINE_SHARD)
                .input('S', Blocks.WET_SPONGE)
                .input('H', Items.HEART_OF_THE_SEA)
                .criterion(hasItem(ModItems.ALBITE_CRYSTAL), conditionsFromItem(ModItems.ALBITE_CRYSTAL))
                .offerTo(consumer, id("prism_of_the_seas"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_HOE)
                .pattern("CC ")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer, id("cobalt_hoe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_SHOVEL)
                .pattern(" C ")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer, id("cobalt_shovel"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_PICKAXE)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer, id("cobalt_pickaxe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_AXE)
                .pattern("CC ")
                .pattern("CS ")
                .pattern(" S ")
                .input('C', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer, id("cobalt_axe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_SWORD)
                .pattern(" C ")
                .pattern(" C ")
                .pattern(" S ")
                .input('C', ModItems.COBALT_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer, id("cobalt_sword"));


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_PICKAXE)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.LITHIUM_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.LITHIUM_INGOT), conditionsFromItem(ModItems.LITHIUM_INGOT))
                .offerTo(consumer, id("lithium_pickaxe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_AXE)
                .pattern(" CC")
                .pattern(" SC")
                .pattern(" S ")
                .input('C', ModItems.LITHIUM_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.LITHIUM_INGOT), conditionsFromItem(ModItems.LITHIUM_INGOT))
                .offerTo(consumer, id("lithium_axe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_HOE)
                .pattern("JJ ")
                .pattern(" S ")
                .pattern(" S ")
                .input('J', ModItems.LITHIUM_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.LITHIUM_INGOT), conditionsFromItem(ModItems.LITHIUM_INGOT))
                .offerTo(consumer, id("lithium_hoe"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_SHOVEL)
                .pattern(" C ")
                .pattern(" S ")
                .pattern(" S ")
                .input('C', ModItems.LITHIUM_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.LITHIUM_INGOT), conditionsFromItem(ModItems.LITHIUM_INGOT))
                .offerTo(consumer, id("lithium_shovel"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LITHIUM_SWORD)
                .pattern(" C ")
                .pattern(" C ")
                .pattern(" S ")
                .input('C', ModItems.LITHIUM_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.LITHIUM_INGOT), conditionsFromItem(ModItems.LITHIUM_INGOT))
                .offerTo(consumer, id("lithium_sword"));

        // -------- MACHINES --------

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FOUNDRY_FURNACE_CONTROLLER)
                .pattern("###")
                .pattern("#*#")
                .pattern("!!!")
                .input('#', ModItems.HEAT_RESISTANT_BRICK)
                .input('*', Blocks.FURNACE)
                .input('!', Items.IRON_INGOT)
                .criterion(hasItem(ModItems.HEAT_RESISTANT_BRICK), conditionsFromItem(ModItems.HEAT_RESISTANT_BRICK))
                .offerTo(consumer);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COBALT_BLAST_FURNACE)
                .pattern("###")
                .pattern("#*#")
                .pattern("!!!")
                .input('!', ModBlocks.HEAT_RESISTANT_BRICKS)
                .input('*', Blocks.BLAST_FURNACE)
                .input('#', ModItems.COBALT_INGOT)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.AIR_INTAKE)
                .pattern("###")
                .pattern("#*#")
                .pattern("###")
                .input('#', ModItems.HEAT_RESISTANT_BRICK)
                .input('*', ModBlocks.COBALT_GRATE)
                .criterion(hasItem(ModItems.COBALT_INGOT), conditionsFromItem(ModItems.COBALT_INGOT))
                .offerTo(consumer);

        // -------- FOUNDRY --------

        FoundrySmeltingRecipeBuilder.smelting(
                ModItems.RAW_LITHIUM, 1,
                ModItems.LITHIUM_INGOT, 1,
                400, 0.7f
        ).offerTo(consumer);

        FoundrySmeltingRecipeBuilder.smelting(
                ModItems.RAW_COBALT, 1,
                ModItems.COBALT_INGOT, 1,
                500, 0.9f
        ).offerTo(consumer);

        FoundrySmeltingRecipeBuilder.smelting(
                Items.COAL, 1,
                ModItems.COKE, 1,
                500, 0.7f
        ).offerTo(consumer);

        // -------- BLASTING --------

        CobaltBlastingRecipeBuilder.smelting(
                ModItems.DUALBLOOM_LEAF, 1,
                ModItems.DUAL_ENERGY_CORE, 1,
                300, 0.3f
        ).offerTo(consumer);

        CobaltBlastingRecipeBuilder.smelting(
                Items.COAL, 1,
                ModItems.COKE, 1,
                350, 0.7f
        ).offerTo(consumer);

        CobaltBlastingRecipeBuilder.smelting(
                ModItems.RAW_COBALT, 1,
                ModItems.COBALT_INGOT, 1,
                350, 0.9f
        ).offerTo(consumer);

        CobaltBlastingRecipeBuilder.smelting(
                ModItems.RAW_LITHIUM, 1,
                ModItems.LITHIUM_INGOT, 1,
                250, 0.7f
        ).offerTo(consumer);

        offerSmelting(consumer, List.of(ModItems.LITHIUM_POWDER), RecipeCategory.MISC, ModItems.LITHIUM_NUGGET, 0.1f, 200, "lithium");
        offerBlasting(consumer, List.of(ModItems.LITHIUM_POWDER), RecipeCategory.MISC, ModItems.LITHIUM_NUGGET, 0.1f, 100, "lithium");
    }

    private static Identifier id(String name) {
        return new Identifier("wpcworld", name);
    }
}
