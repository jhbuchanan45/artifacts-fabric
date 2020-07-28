package artifacts.common.init;

import artifacts.Artifacts;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class LootTables {
    public static final Identifier MIMIC = new Identifier(Artifacts.MOD_ID, "entities/mimic");
    public static final Identifier CAMPSITE_CHEST = new Identifier(Artifacts.MOD_ID, "chests/campsite_chest");

    public static final List<Identifier> LOOT_TABLE_IDS = Arrays.asList(
            new Identifier("minecraft", "chests/village/village_armorer"),
            new Identifier("minecraft", "chests/village/village_butcher"),
            new Identifier("minecraft", "chests/village/village_tannery"),
            new Identifier("minecraft", "chests/village/village_temple"),
            new Identifier("minecraft", "chests/village/village_toolsmith"),
            new Identifier("minecraft", "chests/village/village_weaponsmith"),
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

    public static void onLootTableLoad(ResourceManager resourceManager, LootManager manager, Identifier id, FabricLootSupplierBuilder supplier, LootTableLoadingCallback.LootTableSetter setter) {
        if (LOOT_TABLE_IDS.contains(id)) {
            supplier.withPool(getInjectPool(id.getPath()));
        }
    }

    public static LootPool getInjectPool(String entryName) {
        return LootPool.builder()
                .with(getInjectEntry(entryName))
                .build();
    }

    private static LootPoolEntry.Builder<?> getInjectEntry(String name) {
        Identifier table = new Identifier(Artifacts.MOD_ID, "inject/" + name);
        return LootTableEntry.builder(table).weight(1);
    }
}
