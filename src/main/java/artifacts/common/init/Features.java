package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.world.CampsiteFeature;
import artifacts.common.world.InCaveWithChance;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class Features {

	public static final Feature<DefaultFeatureConfig> CAMPSITE_FEATURE = Registry.register(
			Registry.FEATURE,
			new Identifier(Artifacts.MODID, "campsite"),
			new CampsiteFeature()
	);

	static {
		// Add feature to biomes
		Decorator<ChanceDecoratorConfig> placement = new InCaveWithChance(ChanceDecoratorConfig.CODEC);

		// TODO: needs fapi #982 merged
		/*Registry.BIOME.forEach(biome -> {
			if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
				biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, CAMPSITE_FEATURE.configure(FeatureConfig.DEFAULT).decorate(placement.configure(new ChanceDecoratorConfig((int) (1 / Artifacts.CONFIG.campsite.genChance)))));
			}
		});*/

		// TODO: registry callback
	}
}
