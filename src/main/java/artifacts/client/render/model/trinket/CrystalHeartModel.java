package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class CrystalHeartModel extends BipedEntityModel<LivingEntity> {

	public CrystalHeartModel(ModelPart root) {
		super(root, RenderLayer::getEntityTranslucent);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 0).cuboid(-4, 0, -2, 8, 12, 4, new Dilation(0.5F));
		ModelPartBuilder heart = ModelPartBuilder.create().uv(0, 16).cuboid(-2.5F, 0, 0, 2, 3, 1);
		ModelPartBuilder heart2 = ModelPartBuilder.create().uv(6, 16).cuboid(0.5F, 0, 0, 2, 3, 1);
		ModelPartBuilder heart3 = ModelPartBuilder.create().uv(0, 20).cuboid(-0.5F, 1, 0, 1, 4, 1);
		ModelPartBuilder heart4 = ModelPartBuilder.create().uv(4, 20).cuboid(-1.5F, 3, 0, 1, 1, 1);
		ModelPartBuilder heart5 = ModelPartBuilder.create().uv(8, 20).cuboid(0.5F, 3, 0, 1, 1, 1);

		root.addChild("body", body, ModelTransform.NONE);
		ModelPartData bodyData = root.getChild("body");
		bodyData.addChild("heart", heart, ModelTransform.pivot(2.5F, 9, -3));
		ModelPartData heartData = root.getChild("body");
		heartData.addChild("heart2", heart2, ModelTransform.NONE);
		heartData.addChild("heart3", heart3, ModelTransform.NONE);
		heartData.addChild("heart4", heart4, ModelTransform.NONE);
		heartData.addChild("heart5", heart5, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 32);
	}
}
