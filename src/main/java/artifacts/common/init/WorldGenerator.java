package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.world.CampsiteFeature;
import artifacts.common.world.InCaveWithChance;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * This is a temporary solution for the lack of a features API in fabric due to changes in 1.16.2
 * This code is mostly taken from Tech Reborn: https://github.com/TechReborn/TechReborn/blob/0b3cb646ceb55140888c8e8de6ac320c1aa6607a/src/main/java/techreborn/world/WorldGenerator.java
 * TODO: use fabric API when it's finished
 */
public class WorldGenerator {

	public static final Decorator<ChanceDecoratorConfig> PLACEMENT = new InCaveWithChance(ChanceDecoratorConfig.CODEC);

	private static final List<Biome> checkedBiomes = new ArrayList<>();

	public static void initWorldGen() {
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
			addFeature(biome, GenerationStep.Feature.UNDERGROUND_STRUCTURES,
					new CampsiteFeature().configure(FeatureConfig.DEFAULT).decorate(PLACEMENT.configure(new ChanceDecoratorConfig((int) (1 / Artifacts.CONFIG.campsite.genChance)))));
		}
	}

	private static void addFeature(Biome biome, GenerationStep.Feature feature, ConfiguredFeature<?, ?> configuredFeature) {
		List<List<Supplier<ConfiguredFeature<?, ?>>>> features = biome.getGenerationSettings().getFeatures();

		int stepIndex = feature.ordinal();

		while (features.size() <= stepIndex) {
			features.add(Lists.newArrayList());
		}

		List<Supplier<ConfiguredFeature<?, ?>>> stepList = features.get(feature.ordinal());
		if (stepList instanceof ImmutableList) {
			features.set(feature.ordinal(), stepList = new ArrayList<>(stepList));
		}

		stepList.add(() -> configuredFeature);
	}
}