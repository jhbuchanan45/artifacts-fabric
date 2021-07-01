package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class FlippersModel extends BipedEntityModel<LivingEntity> {

	public FlippersModel(ModelPart root) {
		super(root);

		setVisible(false);

		leftLeg.visible = true;
		rightLeg.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder flipperLeft = ModelPartBuilder.create().uv(0, 32).cuboid(-2, 11.5F, -16, 9, 1, 20);
		ModelPartBuilder flipperRight = ModelPartBuilder.create().uv(0, 52).cuboid(-7, 11.5F, -16, 9, 1, 20);

		ModelPartData leftLegData = root.getChild("left_leg");
		ModelPartData rightLegData = root.getChild("right_leg");

		leftLegData.addChild("flipper_left", flipperLeft, ModelTransform.NONE);
		rightLegData.addChild("flipper_right", flipperRight, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 96);
	}
}
