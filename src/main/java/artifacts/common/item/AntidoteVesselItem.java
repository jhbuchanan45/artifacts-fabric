package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.AntidoteVesselModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class AntidoteVesselItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/antidote_vessel.png");

    public AntidoteVesselItem() {
        super(new Settings(), "antidote_vessel");
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            protected SoundEvent getEquipSound() {
                return SoundEvents.ITEM_BOTTLE_FILL;
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity entity) {
                Map<StatusEffect, StatusEffectInstance> effects = new HashMap<>();

                entity.getActiveStatusEffects().forEach((effect, instance) -> {
                    if (!effect.isInstant() && !effect.isBeneficial() && instance.getDuration() > 80) {
                        effects.put(effect, instance);
                    }
                });

                effects.forEach((effect, instance) -> {
                    entity.removeStatusEffectInternal(effect);
                    entity.addStatusEffect(new StatusEffectInstance(effect, 80, instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles(), instance.shouldShowIcon()));
                });
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected AntidoteVesselModel getModel() {
                if (model == null) {
                    model = new AntidoteVesselModel();
                }
                return (AntidoteVesselModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        });
    }
}
