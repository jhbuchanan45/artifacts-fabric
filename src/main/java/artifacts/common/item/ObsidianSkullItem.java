package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ObsidianSkullModel;
import artifacts.common.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

public class ObsidianSkullItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/obsidian_skull.png");

    public ObsidianSkullItem() {
        super(new Settings(), "obsidian_skull");
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            @Environment(EnvType.CLIENT)
            protected ObsidianSkullModel getModel() {
                if (model == null) {
                    model = new ObsidianSkullModel();
                }
                return (ObsidianSkullModel) model;
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
        public static void onLivingHurt(LivingHurtEvent event) {
            if (!event.getEntity().world.isClient && event.getAmount() >= 1 && (event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.IN_FIRE || event.getSource() == DamageSource.LAVA) && event.getEntity() instanceof PlayerEntity) {
                if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.OBSIDIAN_SKULL, event.getEntityLiving()).isPresent() && !((PlayerEntity) event.getEntity()).getItemCooldownManager().isCoolingDown(Items.OBSIDIAN_SKULL)) {
                    event.getEntityLiving().addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 0, false, true));
                    ((PlayerEntity) event.getEntity()).getItemCooldownManager().set(Items.OBSIDIAN_SKULL, 1200);
                }
            }
        }
    }
}
