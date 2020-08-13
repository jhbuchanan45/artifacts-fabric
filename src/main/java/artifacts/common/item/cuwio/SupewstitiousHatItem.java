package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.SupewstitiousHatModew;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class SupewstitiousHatItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/superstitious_hat.png");

	public SupewstitiousHatItem() {
		super(new Item.Settings());
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected SupewstitiousHatModew getModew() {
				if (modew == null) {
					modew = new SupewstitiousHatModew();
				}
				return (SupewstitiousHatModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
