package artifacts.common.item.cuwio;

import artifacts.cwient.wendew.modew.cuwio.DwinkingHatModew;
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

public class DwinkingHatItem extends CuwioAwtifactItem {

	private final Identifiew textuwe;

	public DwinkingHatItem(Identifiew textuwe) {
		super(new Item.Settings());
		this.textuwe = textuwe;
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			protected SoundEvent getEquipSound() {
				return SoundEvents.ITEM_BOTTWE_FIWW;
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected DwinkingHatModew getModew() {
				if (modew == null) {
					modew = new DwinkingHatModew();
				}
				return (DwinkingHatModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return textuwe;
			}
		};
	}
}
