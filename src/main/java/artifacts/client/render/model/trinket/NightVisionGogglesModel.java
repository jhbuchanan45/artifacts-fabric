package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class NightVisionGogglesModel extends BipedEntityModel<LivingEntity> {

	public NightVisionGogglesModel(ModelPart root) {
		super(root);

		setVisible(false);
		head.visible = true;
		hat.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder goggles = ModelPartBuilder.create().uv(0, 37).cuboid(-4, -6, -5 + 0.05F, 8, 4, 1);
		ModelPartBuilder eyeLeft = ModelPartBuilder.create().uv(0, 32).cuboid(1.5F, -5, -8 + 0.05F, 2, 2, 3);
		ModelPartBuilder eyeRight = ModelPartBuilder.create().uv(10, 32).cuboid(-3.5F, -5, -8 + 0.05F, 2, 2, 3);

		ModelPartData headData = root.getChild("head");
		headData.addChild("goggles", goggles, ModelTransform.NONE);
		headData.addChild("eyeLeft", eyeLeft, ModelTransform.NONE);
		headData.addChild("eyeRight", eyeRight, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
