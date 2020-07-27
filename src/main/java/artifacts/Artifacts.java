package artifacts;

import artifacts.common.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.SlotTypePreset;

public class Artifacts implements ModInitializer {

    public static final String MODID = "artifacts";

    public static final ItemGroup CREATIVE_TAB = FabricItemGroupBuilder.build(
            new Identifier(MODID, "item_group"),
            () -> new ItemStack(Items.PLASTIC_DRINKING_HAT)
    );

    @Override
    public void onInitialize() {
        Features.registerFeatures();
    }

    public static class RegistryEvents {

        @SubscribeEvent
        public static void enqueueIMC(final InterModEnqueueEvent event) {
            SlotTypePreset[] Types = {SlotTypePreset.HEAD, SlotTypePreset.NECKLACE, SlotTypePreset.BELT};
            for (SlotTypePreset type : Types) {
                InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> type.getMessageBuilder().build());
            }
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HANDS.getMessageBuilder().size(2).build());
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("feet").priority(220).icon(PlayerScreenHandler.EMPTY_BOOTS_SLOT_TEXTURE).build());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            Items.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
            Entities.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
            SoundEvents.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerLootModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
            LootModifiers.register(event.getRegistry());
        }
    }
}
