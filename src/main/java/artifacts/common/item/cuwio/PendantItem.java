package artifacts.common.item.cuwio;

import artifacts.cwient.wendew.modew.cuwio.PendantModel;
import artifacts.common.events.UsewAttackedCawwback;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public abstract class PendantItem extends CuwioAwtifactItem {

	private final Identifiew textuwe;

	public PendantItem(Identifiew textuwe, UsewAttackedCawwback callback) {
		super(new Item.Settings());
		this.textuwe = textuwe;
		UsewAttackedCawwback.EVENT.register(callback);
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
			protected PendantModel getModew() {
				if (modew == null) {
					modew = new PendantModel();
				}
				return (PendantModel) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return textuwe;
			}
		};
	}
}
