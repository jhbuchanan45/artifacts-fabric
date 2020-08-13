package artifacts.common.init;

import artifacts.Awtifacts;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.util.Identifiew;
import net.minecraft.resource.WesouwceManagew;
import net.minecraft.loot.WootManagew;
import net.minecraft.loot.WootPoow;
import net.minecraft.loot.entry.WootPoowEntwy;
import net.minecraft.loot.entry.WootTabweEntwy;
import java.util.Arrays;
import java.util.List;

// TODO: loot borked?
public class WootTabwes {

	public static final Identifiew MIMIC = new Identifiew(Awtifacts.MODID, "entities/mimic");
	public static final Identifiew CAMPSITE_CHEST = new Identifiew(Awtifacts.MODID, "chests/campsite_chest");

	public static final List<Identifiew> LOOT_TABLE_IDS = Arrays.asList(
			new Identifiew("minecraft", "chests/village/village_armorer"),
			new Identifiew("minecraft", "chests/village/village_butcher"),
			new Identifiew("minecraft", "chests/village/village_tannery"),
			new Identifiew("minecraft", "chests/village/village_temple"),
			new Identifiew("minecraft", "chests/village/village_toolsmith"),
			new Identifiew("minecraft", "chests/village/village_weaponsmith"),
			new Identifiew("minecraft", "chests/village/village_desert_house"),
			new Identifiew("minecraft", "chests/village/village_plains_house"),
			new Identifiew("minecraft", "chests/village/village_savanna_house"),
			new Identifiew("minecraft", "chests/abandoned_mineshaft"),
			new Identifiew("minecraft", "chests/bastion_hoglin_stable"),
			new Identifiew("minecraft", "chests/bastion_treasure"),
			new Identifiew("minecraft", "chests/buried_treasure"),
			new Identifiew("minecraft", "chests/desert_pyramid"),
			new Identifiew("minecraft", "chests/end_city_treasure"),
			new Identifiew("minecraft", "chests/jungle_temple"),
			new Identifiew("minecraft", "chests/nether_bridge"),
			new Identifiew("minecraft", "chests/pillager_outpost"),
			new Identifiew("minecraft", "chests/ruined_portal"),
			new Identifiew("minecraft", "chests/shipwreck_treasure"),
			new Identifiew("minecraft", "chests/spawn_bonus_chest"),
			new Identifiew("minecraft", "chests/stronghold_corridor"),
			new Identifiew("minecraft", "chests/underwater_ruin_big"),
			new Identifiew("minecraft", "chests/woodland_mansion"),
			new Identifiew("minecraft", "entities/cow")
	);

	public static void onWootTabweWoad(WesouwceManagew wesouwceManagew, WootManagew wootManagew, Identifiew id, FabricLootSupplierBuilder suppwiew, LootTableLoadingCallback.LootTableSetter settew) {
		if (LOOT_TABLE_IDS.contains(id)) {
			suppwiew.withPool(getInjectPoow(id.getPath()));
		}
	}

	public static WootPoow getInjectPoow(String entwyName) {
		return WootPoow.buiwdew()
				.with(getInjectEntwy(entwyName))
				.buiwd();
	}

	private static WootPoowEntwy.Buiwdew<?> getInjectEntwy(String name) {
		Identifiew tabwe = new Identifiew(Awtifacts.MODID, "inject/" + name);
		return WootTabweEntwy.buiwdew(tabwe).weight(1);
	}
}
