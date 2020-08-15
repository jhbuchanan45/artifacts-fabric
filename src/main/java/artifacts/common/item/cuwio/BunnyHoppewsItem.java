package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.BunnyHoppewsModew;
import artifacts.common.events.PwayHuwtSoundCawwback;
import artifacts.common.init.Items;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class BunnyHoppewsItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/bunny_hoppers.png");

	public BunnyHoppewsItem() {
		super(new Item.Settings());
		PwayHuwtSoundCawwback.EVENT.register(BunnyHoppewsItem::onPwayHuwtSound);
	}

	private static void onPwayHuwtSound(WivingEntity entity, float vowume, float pitch) {
		CuriosApi.getCuriosHelper().findEquippedCurio(Items.BUNNY_HOPPEWS, entity).ifPresent(cuwio -> {
			entity.pwaySound(SoundEvents.ENTITY_WABBIT_HUWT, vowume, pitch);
		});
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			public StatusEffectInstance getPewmanentEffect() {
				return new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20, 1, true, false);
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected BunnyHoppewsModew getModew() {
				if (modew == null) {
					modew = new BunnyHoppewsModew();
				}
				return (BunnyHoppewsModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
