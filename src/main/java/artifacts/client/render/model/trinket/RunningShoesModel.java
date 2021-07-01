package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class RunningShoesModel extends BipedEntityModel<LivingEntity> {

	public RunningShoesModel(ModelPart root) {
		super(root);

		setVisible(false);
		leftLeg.visible = rightLeg.visible = true;
	}

	public static TexturedModelData getTexturedGloveData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(1), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder leftLeg = ModelPartBuilder.create().uv(0, 0).cuboid(-2, 0, -2, 4, 12, 4, new Dilation(0.5F));
		ModelPartBuilder rightLeg = ModelPartBuilder.create().uv(16, 0).cuboid(-2, 0, -2, 4, 12, 4, new Dilation(0.5F));
		ModelPartBuilder leftShoeTip = ModelPartBuilder.create().uv(0, 16).cuboid(-2, 9.375F, -3.625F, 4, 3, 1, new Dilation(0.5F, 0.125F, 0.125F));
		ModelPartBuilder rightShoeTip = ModelPartBuilder.create().uv(16, 16).cuboid(-2, 9.375F, -3.625F, 4, 3, 1, new Dilation(0.5F, 0.125F, 0.125F));

		ModelPartData leftLegData = root.addChild("left_leg", leftLeg, ModelTransform.NONE);
		ModelPartData rightLegData = root.addChild("right_leg", rightLeg, ModelTransform.NONE);
		leftLegData.addChild("left_shoe_tip", leftShoeTip, ModelTransform.NONE);
		rightLegData.addChild("right_shoe_tip", rightShoeTip, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 32);
	}
}
