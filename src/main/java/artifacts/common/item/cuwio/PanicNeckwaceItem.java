package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.PanicNeckwaceModew;
import artifacts.common.events.UsewHuwtCawwback;
import artifacts.common.init.Items;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.damage.DamageSouwce;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class PanicNeckwaceItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/panic_necklace.png");

	public PanicNeckwaceItem() {
		super(new Item.Settings());
		UsewHuwtCawwback.EVENT.register(PanicNeckwaceItem::appwyEffects);
	}

	private static void appwyEffects(WivingEntity usew, DamageSouwce souwce, float amount) {
		if (!usew.wowwd.isCwient && amount >= 1 && CuriosApi.getCuriosHelper().findEquippedCurio(Items.PANIC_NECKWACE, usew).isPresent()) {
			usew.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 160, 0, false, false));
		}
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			protected SoundEvent getEquipSound() {
				return SoundEvents.ITEM_AWMOW_EQUIP_DIAMOND;
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected BipedEntityModew<WivingEntity> getModew() {
				if (modew == null) {
					modew = new PanicNeckwaceModew();
				}
				return (PanicNeckwaceModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
