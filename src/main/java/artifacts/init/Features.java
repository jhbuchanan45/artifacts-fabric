package artifacts.init;

import artifacts.Artifacts;
import artifacts.world.CampsiteFeature;
import artifacts.world.InCaveWithChance;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.function.Predicate;

// TODO: Biome Modifications API is experimental, remove suppress warning when stable
@SuppressWarnings("deprecation")
public class Features {

	public static final Feature<DefaultFeatureConfig> CAMPSITE_FEATURE = Registry.register(
			Registry.FEATURE,
			new Identifier(Artifacts.MODID, "campsite"),
			new CampsiteFeature()
	);
	public static final Decorator<ChanceDecoratorConfig> DECORATOR = Registry.register(
			Registry.DECORATOR,
			new Identifier(Artifacts.MODID, "incavewithchance"),
			new InCaveWithChance(ChanceDecoratorConfig.CODEC)
	);
	public static final ConfiguredFeature<?, ?> CAMPSITE_CONFIGURED_FEATURE = Registry.register(
			BuiltinRegistries.CONFIGURED_FEATURE,
			new Identifier(Artifacts.MODID, "campsite"),
			CAMPSITE_FEATURE.configure(FeatureConfig.DEFAULT).decorate(DECORATOR.configure(
					new ChanceDecoratorConfig(Artifacts.CONFIG.worldgen.campsite.genChance)
			))
	);

	public static void register() {
		Predicate<BiomeSelectionContext> biomeSelector = BiomeSelectors.foundInOverworld();
		BiomeModifications.addFeature(biomeSelector, GenerationStep.Feature.UNDERGROUND_STRUCTURES,
				BuiltinRegistries.CONFIGURED_FEATURE.getKey(CAMPSITE_CONFIGURED_FEATURE)
						.orElseThrow(() -> new RuntimeException("Failed to get feature from registry")));
	}

	private Features() {
	}
}
