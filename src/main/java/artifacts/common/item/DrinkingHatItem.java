package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.DrinkingHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class DrinkingHatItem extends CurioArtifactItem {

	private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/plastic_drinking_hat.png");
	private static final Identifier TEXTURE_NOVELTY = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/novelty_drinking_hat.png");

	private final boolean isNoveltyHat;

	public DrinkingHatItem(boolean isNoveltyHat) {
		super(new Settings());
		this.isNoveltyHat = isNoveltyHat;
	}

	@Override
	public Rarity getRarity(ItemStack stack) {
		return isNoveltyHat ? Rarity.EPIC : Rarity.RARE;
	}

	@Override
	ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			@Override
			protected SoundEvent getEquipSound() {
				return SoundEvents.ITEM_BOTTLE_FILL;
			}
		};
	}

	@Override
	IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableCurio() {
			private Object model;

			@Override
			@Environment(EnvType.CLIENT)
			protected DrinkingHatModel getModel() {
				if (model == null) {
					model = new DrinkingHatModel();
				}
				return (DrinkingHatModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return isNoveltyHat ? TEXTURE_NOVELTY : TEXTURE_DEFAULT;
			}
		};
	}
}
