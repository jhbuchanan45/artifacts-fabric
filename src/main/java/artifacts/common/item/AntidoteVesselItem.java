package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.AntidoteVesselModel;
import artifacts.mixins.StatusEffectAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.HashMap;
import java.util.Map;

public class AntidoteVesselItem extends CurioArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/antidote_vessel.png");

    public AntidoteVesselItem() {
        super(new Settings());
    }

    @Override
    ICurio attachCurio(ItemStack stack) {
        return new Curio(this) {
            @Override
            protected SoundEvent getEquipSound() {
                return SoundEvents.ITEM_BOTTLE_FILL;
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity entity) {
                Map<StatusEffect, StatusEffectInstance> effects = new HashMap<>();

                entity.getActiveStatusEffects().forEach((effect, instance) -> {
                    // TODO: this is very likely not going to work but I wanna try it anyways (use accessor instead)
                    if (!effect.isInstant() && !((StatusEffectAccessor) effect).invokeIsBeneficial() && instance.getDuration() > 80) {
                        effects.put(effect, instance);
                    }
                });

                effects.forEach((effect, instance) -> {
                    entity.removeStatusEffectInternal(effect);
                    entity.addStatusEffect(new StatusEffectInstance(effect, 80, instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles(), instance.shouldShowIcon()));
                });
            }
        };
    }

    @Override
    IRenderableCurio attachRenderableCurio(ItemStack stack) {
        return new RenderableCurio() {
            private Object model;

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
        };
    }
}
