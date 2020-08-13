package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.ObsidianSkuwwModew;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class ObsidianSkuwwItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/obsidian_skull.png");

	public ObsidianSkuwwItem() {
		super(new Item.Settings());
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected ObsidianSkuwwModew getModew() {
				if (modew == null) {
					modew = new ObsidianSkuwwModew();
				}
				return (ObsidianSkuwwModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
