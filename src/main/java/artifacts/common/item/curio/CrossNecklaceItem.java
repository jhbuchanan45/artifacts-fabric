package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.CrossNecklaceModel;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class CrossNecklaceItem extends CurioArtifactItem {

	public static final double HURT_RESISTANCE_MULTIPLIER = 3; // Hurt invuln is multiplied by this factor
	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/cross_necklace.png");

	public CrossNecklaceItem() {
		super(new Item.Settings());
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
			protected CrossNecklaceModel getModel() {
				if (model == null) {
					model = new CrossNecklaceModel();
				}
				return (CrossNecklaceModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return TEXTURE;
			}
		};
	}
}
