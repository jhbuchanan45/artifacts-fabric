package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.FwippewsModew;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class FwippewsItem extends CuwioAwtifactItem {

	public static final int SWIM_SPEED_MULTIPLIER = 2;
	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/flippers.png");


	public FwippewsItem() {
		super(new Item.Settings());
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected FwippewsModew getModew() {
				if (modew == null) {
					modew = new FwippewsModew();
				}
				return (FwippewsModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
