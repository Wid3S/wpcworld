package net.wides.wpcworld.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.wides.wpcworld.WPCWorld;
import net.wides.wpcworld.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> LITHIUM_ORE_KEY = registryKey("lithium_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_LITHIUM_ORE_KEY = registryKey("deepslate_lithium_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registryKey("cobalt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_COBALT_ORE_KEY = registryKey("deepslate_cobalt_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_ALBITE_ORE_KEY = registryKey("deepslate_albite_ore");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldLithiumOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.LEPIDOLITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldDeepslateLithiumOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldCobaltOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.COBALT_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldDeepslateCobaltOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_COBALT_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> overworldDeepslateAlbiteOres =
                List.of(OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_ALBITE_ORE.getDefaultState()));


        register(context, LITHIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldLithiumOres, 5));
        register(context, DEEPSLATE_LITHIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldDeepslateLithiumOres, 3));

        register(context, COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldCobaltOres, 3));
        register(context, DEEPSLATE_COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldDeepslateCobaltOres, 4));

        register(context, DEEPSLATE_ALBITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldDeepslateAlbiteOres, 4));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(WPCWorld.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}