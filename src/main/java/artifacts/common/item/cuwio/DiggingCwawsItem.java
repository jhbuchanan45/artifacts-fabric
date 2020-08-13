package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.CwawsModew;
import artifacts.common.item.WendewabweGwoveCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class DiggingCwawsItem extends CuwioAwtifactItem {

	public static final int NEW_BASE_MINING_WEVEW = 2;
	public static final float MINING_SPEED_INCWEASE = 4;

	private static final Identifiew TEXTUWE_DEFAUWT = new Identifiew(Awtifacts.MODID, "textures/entity/curio/digging_claws_default.png");
	private static final Identifiew TEXTUWE_SWIM = new Identifiew(Awtifacts.MODID, "textures/entity/curio/digging_claws_default.png");

	public DiggingCwawsItem() {
		super(new Item.Settings());
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweGwoveCuwio() {
			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE_DEFAUWT;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getSwimTextuwe() {
				return TEXTUWE_SWIM;
			}

			@Environment(EnvType.CLIENT)
			protected CwawsModew getSwimModew() {
				if (modewSwim == null) {
					modewSwim = new CwawsModew(true);
				}
				return (CwawsModew) modewSwim;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected CwawsModew getModew() {
				if (modewDefauwt == null) {
					modewDefauwt = new CwawsModew(false);
				}
				return (CwawsModew) modewDefauwt;
			}
		};
	}
}
