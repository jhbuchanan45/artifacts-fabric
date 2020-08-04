package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.FlippersModel;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class FlippersItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/flippers.png");
	public static final int SWIM_SPEED_MULTIPLIER = 2;


	public FlippersItem() {
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
			protected FlippersModel getModel() {
				if (model == null) {
					model = new FlippersModel();
				}
				return (FlippersModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return TEXTURE;
			}
		};
	}
}
