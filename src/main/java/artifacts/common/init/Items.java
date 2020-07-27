package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.item.*;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    // Head
    public static final Item SNORKEL = register("snorkel", new SnorkelItem());
    public static final Item PLASTIC_DRINKING_HAT = register("plastic_drinking_hat", new DrinkingHatItem(false));
    public static final Item NOVELTY_DRINKING_HAT = register("novelty_drinking_hat", new DrinkingHatItem(true));
    public static final Item NIGHT_VISION_GOGGLES = register("night_vision_goggles", new NightVisionGogglesItem());
    public static final Item SUPERSTITIOUS_HAT = register("superstitious_hat", new SuperstitiousHatItem());

    // Necklace
    public static final Item PANIC_NECKLACE = register("panic_necklace", new PanicNecklaceItem());
    public static final Item SHOCK_PENDANT = register("shock_pendant", new PendantItem(new Identifier(Artifacts.MOD_ID, "textures/entity/curio/shock_pendant.png")));
    public static final Item FLAME_PENDANT = register("flame_pendant", new PendantItem(new Identifier(Artifacts.MOD_ID, "textures/entity/curio/flame_pendant.png")));
    public static final Item THORN_PENDANT = register("thorn_pendant", new PendantItem(new Identifier(Artifacts.MOD_ID, "textures/entity/curio/thorn_pendant.png")));
    public static final Item CROSS_NECKLACE = register("cross_necklace", new CrossNecklaceItem());
    public static final Item LUCKY_SCARF = register("lucky_scarf", new LuckyScarfItem());
    public static final Item SCARF_OF_INVISIBILITY = register("scarf_of_invisibility", new ScarfOfInvisibilityItem());

    // Hands
    public static final Item FIRE_GAUNTLET = register("fire_gauntlet", new FireGauntletItem());
    public static final Item FERAL_CLAWS = register("feral_claws", new FeralClawsItem());
    public static final Item POCKET_PISTON = register("pocket_piston", new PocketPistonItem());
    public static final Item POWER_GLOVE = register("power_glove", new PowerGloveItem());
    public static final Item DIGGING_CLAWS = register("digging_claws", new DiggingClawsItem());

    // Belt
    public static final Item OBSIDIAN_SKULL = register("obsidian_skull", new ObsidianSkullItem());
    public static final Item ANTIDOTE_VESSEL = register("antidote_vessel", new AntidoteVesselItem());
    public static final Item UNIVERSAL_ATTRACTOR = register("universal_attractor", new UniversalAttractorItem());

    // Feet
    public static final Item FLIPPERS = register("flippers", new FlippersItem());
    public static final Item STEADFAST_SPIKES = register("steadfast_spikes", new SteadfastSpikesItem());
    public static final Item KITTY_SLIPPERS = register("kitty_slippers", new KittySlippersItem());
    public static final Item RUNNING_SHOES = register("running_shoes", new RunningShoesItem());
    public static final Item BUNNY_HOPPERS = register("bunny_hoppers", new BunnyHoppersItem());

    // Food
    public static final Item EVERLASTING_BEEF = register("everlasting_beef", new EverlastingFoodItem(new Item.Settings().food(FoodComponents.BEEF)));
    public static final Item ETERNAL_STEAK = register("eternal_steak", new EverlastingFoodItem(new Item.Settings().food(FoodComponents.COOKED_BEEF)));

    // Misc
    public static final Item UMBRELLA = register("umbrella", new UmbrellaItem());
    public static final Item MIMIC_SPAWN_EGG = register("mimic_spawn_egg", new SpawnEggItem(Entities.MIMIC, 0x805113, 0x212121, new Item.Settings().group(ItemGroup.MISC)));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Artifacts.MOD_ID, name), item);
    }
}
