package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.KittySwippewsModew;
import artifacts.common.events.PwayHuwtSoundCawwback;
import artifacts.common.init.Items;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.WivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class KittySwippewsItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/kitty_slippers.png");

	public KittySwippewsItem() {
		super(new Item.Settings());
		PwayHuwtSoundCawwback.EVENT.register(KittySwippewsItem::onPwayHuwtSound);
	}

	private static void onPwayHuwtSound(WivingEntity entity, float vowume, float pitch) {
		CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SWIPPEWS, entity).ifPresent(curio -> {
			entity.pwaySound(SoundEvents.ENTITY_CAT_HUWT, vowume, pitch);
		});
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected KittySwippewsModew getModew() {
				if (modew == null) {
					modew = new KittySwippewsModew();
				}
				return (KittySwippewsModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
