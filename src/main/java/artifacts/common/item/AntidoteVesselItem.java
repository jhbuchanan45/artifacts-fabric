package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.AntidoteVesselModel;
import artifacts.common.extensions.StatusEffectInstanceExtension;
import artifacts.mixins.item.antidotevessel.StatusEffectAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

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
				// Reduce duration of all negative status effects to 80
				entity.getActiveStatusEffects().forEach((effect, instance) -> {
					if (!effect.isInstant() && ((StatusEffectAccessor) effect).getType() != StatusEffectType.BENEFICIAL && instance.getDuration() > 80) {
						((StatusEffectInstanceExtension) instance).artifacts$setDuration(80);
					}
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
