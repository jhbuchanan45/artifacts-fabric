package artifacts;

import artifacts.common.config.ModConfig;
import artifacts.common.init.*;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeInfo;
import top.theillusivec4.curios.api.SlotTypePreset;

public class Artifacts implements ModInitializer {

	public static final String MOD_ID = "artifacts";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "item_group"),
			() -> new ItemStack(Items.PLASTIC_DRINKING_HAT)
	);
	public static ModConfig CONFIG;  // TODO: we're not even using this

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void onInitialize() {
		// Config
		AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(Toml4jConfigSerializer::new));
		CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

		// Curios setup
		SlotTypePreset[] types = {SlotTypePreset.HEAD, SlotTypePreset.NECKLACE, SlotTypePreset.BELT};
		for (SlotTypePreset type : types) {
			CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, type.getInfoBuilder().build());
		}
		CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, SlotTypePreset.HANDS.getInfoBuilder().size(2).build());
		CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, new SlotTypeInfo.Builder("feet").priority(220).icon(PlayerScreenHandler.EMPTY_BOOTS_SLOT_TEXTURE).build());

		// LootTable setup
		LootTableLoadingCallback.EVENT.register(LootTables::onLootTableLoad);

		// Force loading init classes
		// Entities is loaded by items, loottables can load lazily (no registration)
		Items.ANTIDOTE_VESSEL.toString();
		Features.CAMPSITE_FEATURE.toString();
		SoundEvents.MIMIC_CLOSE.toString();

		// ToolHandlers
		// TODO: create a test mod for these
		ToolHandlers.register();
	}
}
