package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.NightVisionGogglesModel;
import artifacts.client.render.model.trinket.ScarfModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class LuckyScarfItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/lucky_scarf.png");

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(ScarfModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new ScarfModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}
	
}
