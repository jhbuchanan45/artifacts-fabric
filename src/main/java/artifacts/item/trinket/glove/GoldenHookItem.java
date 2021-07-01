package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.GloveModel;
import artifacts.client.render.model.trinket.GoldenHookModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;

public class GoldenHookItem extends GloveItem {

	private static final Identifier TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/golden_hook_default.png");
	private static final Identifier TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/golden_hook_slim.png");

	@Override
	@Environment(EnvType.CLIENT)
	protected GloveModel createModel(boolean smallArms) {
		return createModel(GoldenHookModel.getTexturedGloveData(Dilation.NONE, smallArms).createModel(), smallArms);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected GloveModel createModel(ModelPart root, boolean smallArms) {
		return new GoldenHookModel(root, smallArms);
	}

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
}
