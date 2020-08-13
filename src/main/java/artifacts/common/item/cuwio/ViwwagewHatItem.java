package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.ViwwagewHatModew;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class ViwwagewHatItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/villager_hat.png");

	public ViwwagewHatItem() {
		super(new Item.Settings());
	}

	@Override
	public ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {

			@Override
			public StatusEffectInstance getPewmanentEffect() {
				return new StatusEffectInstance(StatusEffects.HEWO_OF_THE_VIWWAGE, 20, 1, true, false);
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected ViwwagewHatModew getModew() {
				if (modew == null) {
					modew = new ViwwagewHatModew();
				}
				return (ViwwagewHatModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
