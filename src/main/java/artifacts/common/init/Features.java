package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.config.Config;
import artifacts.common.world.CampsiteFeature;
import artifacts.common.world.InCaveWithChance;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class Features {

    public static final Feature<DefaultFeatureConfig> CAMPSITE_FEATURE = new CampsiteFeature();

    public static void registerFeatures(IForgeRegistry<Feature<?>> registry) {
        registry.register(CAMPSITE_FEATURE.setRegistryName(new Identifier(Artifacts.MODID, "campsite")));
    }

    public static void addFeatures() {
        Decorator<ChanceDecoratorConfig> placement = new InCaveWithChance(ChanceDecoratorConfig.field_24980);

        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
                biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, CAMPSITE_FEATURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(placement.configure(new ChanceDecoratorConfig((int) (1 / Config.campsiteChance)))));
            }
        }
    }
}
