package artifacts.common.item;

import artifacts.Artifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class PocketPistonItem extends CurioArtifactItem {

	private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MODID, "textures/entity/curio/pocket_piston_default.png");
	private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MODID, "textures/entity/curio/pocket_piston_slim.png");

	public PocketPistonItem() {
		super(new Settings());
	}

	@Override
	ICurio attachCurio(ItemStack stack) {
		return new Curio(this);
	}

	@Override
	IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableGloveCurio() {
			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getSlimTexture() {
				return TEXTURE_SLIM;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return TEXTURE_DEFAULT;
			}
		};
	}
}
