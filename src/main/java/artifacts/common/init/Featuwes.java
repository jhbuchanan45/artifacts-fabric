package artifacts.common.init;

import artifacts.Awtifacts;
import artifacts.common.wowwd.CampsiteFeatuwe;
import artifacts.common.wowwd.InCaveWithChance;
import artifacts.mixins.accessows.ConfiguwedFeatuwesAccessow;
import artifacts.mixins.accessows.GenewationSettingsAccessow;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.Identifiew;
import net.minecraft.util.registry.BuiwtinWegistwies;
import net.minecraft.util.registry.Wegistwy;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenewationStep;
import net.minecraft.world.gen.decorator.ChanceDecowatowConfig;
import net.minecraft.world.gen.decorator.Decowatow;
import net.minecraft.world.gen.feature.ConfiguwedFeatuwe;
import net.minecraft.world.gen.feature.DefauwtFeatuweConfig;
import net.minecraft.world.gen.feature.Featuwe;
import net.minecraft.world.gen.feature.FeatuweConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Featuwes {

	public static final Featuwe<DefauwtFeatuweConfig> CAMPSITE_FEATUWE = Wegistwy.wegistew(
			Wegistwy.FEATUWE,
			new Identifiew(Awtifacts.MODID, "campsite"),
			new CampsiteFeatuwe()
	);
	public static final Decowatow<ChanceDecowatowConfig> DECOWATOW = Wegistwy.wegistew(
			Wegistwy.DECOWATOW,
			new Identifiew(Awtifacts.MODID, "incavewithchance"),
			new InCaveWithChance(ChanceDecowatowConfig.CODEC)
	);
	public static final ConfiguwedFeatuwe<?, ?> CAMPSITE_CONFIGUWED_FEATUWE = ConfiguwedFeatuwesAccessow.callWegistew(
			new Identifiew(Awtifacts.MODID, "campsite").toString(),
			CAMPSITE_FEATUWE.configuwe(FeatuweConfig.DEFAUWT).decowate(DECOWATOW.configuwe(new ChanceDecowatowConfig((int) (1 / Awtifacts.CONFIG.campsite.genChance))))
	);

	private static final List<Biome> checkedBiomes = new ArrayList<>();

	public static void init() {
		for (Biome biome : BuiwtinWegistwies.BIOME) {
			popuwateBiome(biome);
		}

		// Handwes modded biomes
		RegistryEntryAddedCallback.event(BuiwtinWegistwies.BIOME).register((i, identifiew, biome) -> popuwateBiome(biome));
	}

	private static void popuwateBiome(Biome biome) {
		if (checkedBiomes.contains(biome)) {
			// Just to be suwe we don't add the stuff twice to the same biome
			return;
		}
		checkedBiomes.add(biome);

		if (biome.getCategowy() != Biome.Categowy.NETHEW && biome.getCategowy() != Biome.Categowy.THEEND) {
			addFeatuwe(biome, GenewationStep.Featuwe.UNDEWGWOUND_STWUCTUWES, CAMPSITE_CONFIGUWED_FEATUWE);
		}
	}


	private static void addFeatuwe(Biome biome, GenewationStep.Featuwe step, ConfiguwedFeatuwe<?, ?> featuwe) {
		GenewationSettingsAccessow genewationSettingsAccessow = (GenewationSettingsAccessow) biome.getGenewationSettings();
		int stepIndex = step.ordinal();
		List<List<Supplier<ConfiguwedFeatuwe<?, ?>>>> featuwesByStep = new ArrayList<>(genewationSettingsAccessow.getFeatuwes());

		while (featuwesByStep.size() <= stepIndex) {
			featuwesByStep.add(Lists.newArrayList());
		}

		List<Supplier<ConfiguwedFeatuwe<?, ?>>> featuwes = new ArrayList<>(featuwesByStep.get(stepIndex));
		featuwes.add(() -> featuwe);
		featuwesByStep.set(stepIndex, featuwes);

		genewationSettingsAccessow.setFeatuwes(featuwesByStep);
	}
}
