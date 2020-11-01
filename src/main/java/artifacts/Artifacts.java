package artifacts;

import artifacts.common.config.ModConfig;
import artifacts.common.init.*;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Artifacts implements ModInitializer {

	public static final String MODID = "artifacts";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MODID, "item_group"),
			() -> new ItemStack(Items.BUNNY_HOPPERS)
	);
	public static ModConfig CONFIG;

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void onInitialize() {
		// Config
		AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(Toml4jConfigSerializer::new));
		Artifacts.CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

		// Trinkets setup
		TrinketSlots.addSlot(SlotGroups.LEGS, Slots.BELT, new Identifier("trinkets", "textures/item/empty_trinket_slot_belt.png"));
		TrinketSlots.addSlot(SlotGroups.CHEST, Slots.NECKLACE, new Identifier("trinkets", "textures/item/empty_trinket_slot_necklace.png"));
		TrinketSlots.addSlot(SlotGroups.HEAD, artifacts.common.trinkets.Slots.HAT, new Identifier(MODID, "textures/item/empty_trinket_slot_hat.png"));
		TrinketSlots.addSlot(SlotGroups.FEET, artifacts.common.trinkets.Slots.SHOES, new Identifier(MODID, "textures/item/empty_trinket_slot_shoes.png"));
		TrinketSlots.addSlot(SlotGroups.HAND, Slots.GLOVES, new Identifier("trinkets", "textures/item/empty_trinket_slot_gloves.png"));
		TrinketSlots.addSlot(SlotGroups.OFFHAND, Slots.GLOVES, new Identifier("trinkets", "textures/item/empty_trinket_slot_gloves.png"));

		// LootTable setup
		LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> LootTables.onLootTableLoad(id, supplier));

		// Force loading init classes
		// Entities is loaded by items, loottables can load lazily (no registration)
		Items.ANTIDOTE_VESSEL.toString();
		SoundEvents.MIMIC_CLOSE.toString();
		Features.register();

		// ToolHandlers
		ToolHandlers.register();
		LOGGER.info("[Artifacts] Finished initialization");
	}
}
