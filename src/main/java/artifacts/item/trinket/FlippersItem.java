package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.DrinkingHatModel;
import artifacts.client.render.model.trinket.FlippersModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class FlippersItem extends TrinketArtifactItem {

	public static final int SWIM_SPEED_MULTIPLIER = 2;
	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/flippers.png");

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(FlippersModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new FlippersModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

}
