package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.world.CampsiteFeature;
import artifacts.common.world.InCaveWithChance;
import artifacts.mixins.accessors.ConfiguredFeaturesAccessor;
import artifacts.mixins.accessors.GenerationSettingsAccessor;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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
	public static final ConfiguredFeature<?, ?> CAMPSITE_CONFIGURED_FEATURE = ConfiguredFeaturesAccessor.register(
			new Identifier(Artifacts.MODID, "campsite").toString(),
			CAMPSITE_FEATURE.configure(FeatureConfig.DEFAULT).decorate(DECORATOR.configure(new ChanceDecoratorConfig((int) (1 / Artifacts.CONFIG.campsite.genChance))))
	);

	private static final List<Biome> checkedBiomes = new ArrayList<>();

	public static void init() {
		for (Biome biome : BuiltinRegistries.BIOME) {
			populateBiome(biome);
		}

		//Handles modded biomes
		RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> populateBiome(biome));
	}

	private static void populateBiome(Biome biome) {
		if (checkedBiomes.contains(biome)) {
			//Just to be sure we dont add the stuff twice to the same biome
			return;
		}
		checkedBiomes.add(biome);

		if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
			addFeature(biome, GenerationStep.Feature.UNDERGROUND_STRUCTURES, CAMPSITE_CONFIGURED_FEATURE);
		}
	}


	private static void addFeature(Biome biome, GenerationStep.Feature step, ConfiguredFeature<?, ?> feature) {
		GenerationSettingsAccessor generationSettingsAccessor = (GenerationSettingsAccessor) biome.getGenerationSettings();
		int stepIndex = step.ordinal();
		List<List<Supplier<ConfiguredFeature<?, ?>>>> featuresByStep = new ArrayList<>(generationSettingsAccessor.getFeatures());

		while (featuresByStep.size() <= stepIndex) {
			featuresByStep.add(Lists.newArrayList());
		}

		List<Supplier<ConfiguredFeature<?, ?>>> features = new ArrayList<>(featuresByStep.get(stepIndex));
		features.add(() -> feature);
		featuresByStep.set(stepIndex, features);

		generationSettingsAccessor.setFeatures(featuresByStep);
	}
}
