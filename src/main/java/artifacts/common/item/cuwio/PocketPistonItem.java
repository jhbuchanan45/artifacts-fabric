package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.common.item.WendewabweGwoveCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class PocketPistonItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE_DEFAUWT = new Identifiew(Awtifacts.MODID, "textures/entity/curio/pocket_piston_default.png");
	private static final Identifiew TEXTUWE_SWIM = new Identifiew(Awtifacts.MODID, "textures/entity/curio/pocket_piston_slim.png");

	public PocketPistonItem() {
		super(new Item.Settings());
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweGwoveCuwio() {
			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getSwimTextuwe() {
				return TEXTUWE_SWIM;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE_DEFAUWT;
			}
		};
	}
}
