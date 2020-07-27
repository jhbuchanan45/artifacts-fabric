package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.DrinkingHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.UseAction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

public class DrinkingHatItem extends ArtifactItem {

    private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/plastic_drinking_hat.png");
    private static final Identifier TEXTURE_NOVELTY = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/novelty_drinking_hat.png");

    private final boolean isNoveltyHat;

    public DrinkingHatItem(String name, boolean isNoveltyHat) {
        super(new Settings(), name);
        this.isNoveltyHat = isNoveltyHat;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return isNoveltyHat ? Rarity.EPIC : Rarity.RARE;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {

            private Object model;

            @Override
            protected SoundEvent getEquipSound() {
                return SoundEvents.ITEM_BOTTLE_FILL;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected DrinkingHatModel getModel() {
                if (model == null) {
                    model = new DrinkingHatModel();
                }
                return (DrinkingHatModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return isNoveltyHat ? TEXTURE_NOVELTY : TEXTURE_DEFAULT;
            }
        });
    }

    @Mod.EventBusSubscriber(modid = Artifacts.MOD_ID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onItemUseStart(LivingEntityUseItemEvent.Start event) {
            if (CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof DrinkingHatItem, event.getEntityLiving()).isPresent()) {
                if (event.getItem().getUseAction() == UseAction.DRINK) {
                    event.setDuration(event.getDuration() / 4);
                }
            }
        }
    }
}
