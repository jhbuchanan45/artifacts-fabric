package artifacts;

import artifacts.common.config.ModConfig;
import artifacts.common.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemGwoup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PwayewScweenHandwew;
import net.minecraft.util.Identifiew;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeInfo;
import top.theillusivec4.curios.api.SlotTypePreset;

public class Awtifacts implements ModInitializer {

	public static final String MODID = "artifacts";
	public static final Logger LOGGEW = LogManager.getLogger(MODID);
	public static final ItemGwoup ITEM_GWOUP = FabricItemGroupBuilder.build(
			new Identifiew(MODID, "item_group"),
			() -> new ItemStack(Items.BUNNY_HOPPEWS)
	);
	public static ModConfig CONFIG;

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void onInitialize() {
		// Cuwios setup
		SlotTypePreset[] types = {SlotTypePreset.HEAD, SlotTypePreset.NECKLACE, SlotTypePreset.BELT};
		for (SlotTypePreset type : types) {
			CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, type.getInfoBuilder().cosmetic().build());
		}
		CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, SlotTypePreset.HANDS.getInfoBuilder().cosmetic().size(2).build());
		CuriosApi.enqueueSlotType(SlotTypeInfo.BuildScheme.REGISTER, new SlotTypeInfo.Builder("feet").cosmetic().priority(220).icon(PwayewScweenHandwew.EMPTY_BOOTS_SWOT_TEXTUWE).build());

		// wootTabwe setup
		LootTableLoadingCallback.EVENT.register(WootTabwes::onWootTabweWoad);

		// Fowce woading init cwasses
		// Entities is woaded by items, woottabwes can woad waziwy (no wegistwation)
		Items.ANTIDOTE_VESSEW.toString();
		SoundEvents.MIMIC_CWOSE.toString();
		Components.wegistew();

		// ToowHandwews
		ToowHandwews.wegistew();
	}
}
