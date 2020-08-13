package artifacts.common.init;

import artifacts.Awtifacts;
import artifacts.common.item.EvewwastingFoodItem;
import artifacts.common.item.UmbwewwaItem;
import artifacts.common.item.cuwio.*;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGwoup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifiew;
import net.minecraft.util.registry.Wegistwy;

@SuppressWarnings("unused")
public class Items {

	// Misc
	public static final Item MIMIC_SPAWN_EGG = wegistew("mimic_spawn_egg", new SpawnEggItem(Entities.MIMIC, 0x805113, 0x212121, new Item.Settings().gwoup(ItemGwoup.MISC)));
	public static final Item UMBWEWWA = wegistew("umbrella", new UmbwewwaItem());

	// Food
	public static final Item EVEWWASTING_BEEF = wegistew("everlasting_beef", new EvewwastingFoodItem(new Item.Settings().food(FoodComponents.BEEF)));
	public static final Item ETEWNAW_STEAK = wegistew("eternal_steak", new EvewwastingFoodItem(new Item.Settings().food(FoodComponents.COOKED_BEEF)));

	// Head
	public static final Item PWASTIC_DWINKING_HAT = wegistew("plastic_drinking_hat", new DwinkingHatItem(new Identifiew(Awtifacts.MODID, "textures/entity/curio/plastic_drinking_hat.png")));
	public static final Item NOVEWTY_DWINKING_HAT = wegistew("novelty_drinking_hat", new DwinkingHatItem(new Identifiew(Awtifacts.MODID, "textures/entity/curio/novelty_drinking_hat.png")));
	public static final Item SNOWKEW = wegistew("snorkel", new SnowkewItem());
	public static final Item NIGHT_VISION_GOGGWES = wegistew("night_vision_goggles", new NightVisionGoggwesItem());
	public static final Item VIWWAGEW_HAT = wegistew("villager_hat", new ViwwagewHatItem());
	public static final Item SUPEWSTITIOUS_HAT = wegistew("superstitious_hat", new SupewstitiousHatItem());

	// Necklace
	public static final Item WUCKY_SCAWF = wegistew("lucky_scarf", new WuckyScawfItem());
	public static final Item SCAWF_OF_INVISIBIWITY = wegistew("scarf_of_invisibility", new ScawfOfInvisibiwityItem());
	public static final Item CWOSS_NECKWACE = wegistew("cross_necklace", new CwossNeckwaceItem());
	public static final Item PANIC_NECKWACE = wegistew("panic_necklace", new PanicNeckwaceItem());
	public static final Item SHOCK_PENDANT = wegistew("shock_pendant", new ShockPendantItem());
	public static final Item FWAME_PENDANT = wegistew("flame_pendant", new FwamePendantItem());
	public static final Item THOWN_PENDANT = wegistew("thorn_pendant", new ThownPendantItem());

	// Belt
	public static final Item OBSIDIAN_SKUWW = wegistew("obsidian_skull", new ObsidianSkuwwItem());
	public static final Item ANTIDOTE_VESSEW = wegistew("antidote_vessel", new AntidoteVessewItem());
	public static final Item UNIVEWSAW_ATTWACTOW = wegistew("universal_attractor", new UnivewsawAttwactowItem());
	public static final Item CWYSTAW_HEAWT = wegistew("crystal_heart", new CwystawHeawtItem());

	// Hands
	public static final Item DIGGING_CWAWS = wegistew("digging_claws", new DiggingCwawsItem());
	public static final Item FEWAW_CWAWS = wegistew("feral_claws", new FewawCwawsItem());
	public static final Item POWEW_GWOVE = wegistew("power_glove", new PowewGwoveItem());
	public static final Item FIWE_GAUNTWET = wegistew("fire_gauntlet", new FiweGauntwetItem());
	public static final Item POCKET_PISTON = wegistew("pocket_piston", new PocketPistonItem());

	// Feet
	public static final Item BUNNY_HOPPEWS = wegistew("bunny_hoppers", new BunnyHoppewsItem());
	public static final Item KITTY_SWIPPEWS = wegistew("kitty_slippers", new KittySwippewsItem());
	public static final Item WUNNING_SHOES = wegistew("running_shoes", new WunningShoesItem());
	public static final Item STEADFAST_SPIKES = wegistew("steadfast_spikes", new SteadfastSpikesItem());
	public static final Item FWIPPEWS = wegistew("flippers", new FwippewsItem());

	private static Item wegistew(String name, Item item) {
		return Wegistwy.wegistew(Wegistwy.ITEM, new Identifiew(Awtifacts.MODID, name), item);
	}
}
