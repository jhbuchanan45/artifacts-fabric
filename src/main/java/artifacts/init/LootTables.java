package artifacts.init;

import artifacts.Artifacts;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class LootTables {

	public static final Identifier MIMIC = new Identifier(Artifacts.MODID, "entities/mimic");
	public static final Identifier CAMPSITE_CHEST = new Identifier(Artifacts.MODID, "chests/campsite_chest");

	public static final List<Identifier> INJECT_TABLE_IDS = Arrays.asList(
			new Identifier("minecraft", "chests/village/village_armorer"),
			new Identifier("minecraft", "chests/village/village_butcher"),
			new Identifier("minecraft", "chests/village/village_tannery"),
			new Identifier("minecraft", "chests/village/village_temple"),
			new Identifier("minecraft", "chests/village/village_toolsmith"),
			new Identifier("minecraft", "chests/village/village_weaponsmith"),
			new Identifier("minecraft", "chests/village/village_desert_house"),
			new Identifier("minecraft", "chests/village/village_plains_house"),
			new Identifier("minecraft", "chests/village/village_savanna_house"),
			new Identifier("minecraft", "chests/village/village_snowy_house"),
			new Identifier("minecraft", "chests/village/village_taiga_house"),
			new Identifier("minecraft", "chests/abandoned_mineshaft"),
			new Identifier("minecraft", "chests/bastion_hoglin_stable"),
			new Identifier("minecraft", "chests/bastion_treasure"),
			new Identifier("minecraft", "chests/buried_treasure"),
			new Identifier("minecraft", "chests/desert_pyramid"),
			new Identifier("minecraft", "chests/end_city_treasure"),
			new Identifier("minecraft", "chests/jungle_temple"),
			new Identifier("minecraft", "chests/nether_bridge"),
			new Identifier("minecraft", "chests/pillager_outpost"),
			new Identifier("minecraft", "chests/ruined_portal"),
			new Identifier("minecraft", "chests/shipwreck_treasure"),
			new Identifier("minecraft", "chests/spawn_bonus_chest"),
			new Identifier("minecraft", "chests/stronghold_corridor"),
			new Identifier("minecraft", "chests/underwater_ruin_big"),
			new Identifier("minecraft", "chests/woodland_mansion"),
			new Identifier("minecraft", "entities/cow")
	);

	public static void onLootTableLoad(Identifier id, FabricLootSupplierBuilder supplier) {
		if (INJECT_TABLE_IDS.contains(id)) {
			supplier.pool(LootPool.builder().with(getInjectEntry(id.getPath())));
		}
	}

	private static LootPoolEntry.Builder<?> getInjectEntry(String name) {
		Identifier table = new Identifier(Artifacts.MODID, "inject/" + name);
		return LootTableEntry.builder(table).weight(1);
	}

	private LootTables() {
	}
}
