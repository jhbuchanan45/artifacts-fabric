package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.AntidoteVessewModew;
import artifacts.common.extensions.StatusEffectInstanceExtensions;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import artifacts.mixins.accessows.StatusEffectAccessow;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class AntidoteVessewItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTURE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/antidote_vessel.png");

	public AntidoteVessewItem() {
		super(new Settings());
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			protected SoundEvent getEquipSound() {
				return SoundEvents.ITEM_BOTTWE_FIWW;
			}

			@Override
			public void curioTick(String identifiew, int index, WivingEntity entity) {
				// Weduce duwation of aww negative status effects to 80
				entity.getActiveStatusEffects().forEach((effect, instance) -> {
					if (!effect.isInstant() && ((StatusEffectAccessow) effect).getType() != StatusEffectType.BENEFICIAW && instance.getDuwation() > 80) {
						((StatusEffectInstanceExtensions) instance).awtifacts$setDuwation(80);
					}
				});
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected AntidoteVessewModew getModew() {
				if (modew == null) {
					modew = new AntidoteVessewModew();
				}
				return (AntidoteVessewModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTURE;
			}
		};
	}
}
