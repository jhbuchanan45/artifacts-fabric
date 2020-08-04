package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.SuperstitiousHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class SuperstitiousHatItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/superstitious_hat.png");

	public SuperstitiousHatItem() {
		super(new Settings());
	}

	@Override
	ICurio attachCurio(ItemStack stack) {
		return new Curio(this);
	}

	@Override
	IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableCurio() {
			private Object model;

			@Override
			@Environment(EnvType.CLIENT)
			protected SuperstitiousHatModel getModel() {
				if (model == null) {
					model = new SuperstitiousHatModel();
				}
				return (SuperstitiousHatModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return TEXTURE;
			}
		};
	}
}
