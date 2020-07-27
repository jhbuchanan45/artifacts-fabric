package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.SuperstitiousHatModel;
import artifacts.common.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;

public class SuperstitiousHatItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/superstitious_hat.png");

    public SuperstitiousHatItem() {
        super(new Settings(), "superstitious_hat");
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            @Environment(EnvType.CLIENT)
            protected SuperstitiousHatModel getModel() {
                if (model == null) {
                    model = new SuperstitiousHatModel();
                }
                return (SuperstitiousHatModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        });
    }

    @Mod.EventBusSubscriber(modid = Artifacts.MOD_ID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onLootingLevel(LootingLevelEvent event) {
            Entity killerEntity = event.getDamageSource().getAttacker();
            if (killerEntity instanceof LivingEntity && CuriosApi.getCuriosHelper().findEquippedCurio(Items.SUPERSTITIOUS_HAT, (LivingEntity) killerEntity).isPresent()) {
                event.setLootingLevel(event.getLootingLevel() + 1);
            }
        }
    }
}
