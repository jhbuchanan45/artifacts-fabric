package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;

public class ClawsModel extends GloveModel {

	public ClawsModel(ModelPart root, boolean smallArms) {
		super(root, smallArms);
	}

	public static TexturedModelData getTexturedGloveData(Dilation dilation, boolean smallArms) {
		ModelData modelData = GloveModel.getTexturedModelData(dilation, smallArms);
		int smallArmsOffset = smallArms ? 1 : 0;
		ModelPartData root = modelData.getRoot();
		ModelPartData rightArm = root.getChild("right_arm");
		ModelPartData leftArm = root.getChild("left_arm");

		ModelPartBuilder clawLeftUpper1 = ModelPartBuilder.create().uv(0, 6).cuboid(3 - smallArmsOffset, 10, -1.5F, 1, 4, 1);
		ModelPartBuilder clawRightUpper1 = ModelPartBuilder.create().uv(0, 38).cuboid(-4 + smallArmsOffset, 10, -1.5F, 1, 4, 1);
		ModelPartBuilder clawLeftUpper2 = ModelPartBuilder.create().uv(8, 6).cuboid(3 - smallArmsOffset, 10, 0.5F, 1, 4, 1);
		ModelPartBuilder clawRightUpper2 = ModelPartBuilder.create().uv(8, 38).cuboid(-4 + smallArmsOffset, 10, 0.5F, 1, 4, 1);
		ModelPartBuilder clawLeftLower1 = ModelPartBuilder.create().uv(0, 0).cuboid(-smallArmsOffset, 10, -1.5F, 3, 5, 1);
		ModelPartBuilder clawRightLower1 = ModelPartBuilder.create().uv(0, 32).cuboid(-3 + smallArmsOffset, 10, -1.5F, 3, 5, 1);
		ModelPartBuilder clawLeftLower2 = ModelPartBuilder.create().uv(8, 0).cuboid(-smallArmsOffset, 10, 0.5F, 3, 5, 1);
		ModelPartBuilder clawRightLower2 = ModelPartBuilder.create().uv(8, 32).cuboid(-3 + smallArmsOffset, 10, 0.5F, 3, 5, 1);

		rightArm.addChild("claw_right_upper1", clawRightUpper1, ModelTransform.NONE);
		rightArm.addChild("claw_right_upper2", clawRightUpper2, ModelTransform.NONE);
		rightArm.addChild("claw_right_lower1", clawRightLower1, ModelTransform.NONE);
		rightArm.addChild("claw_right_lower2", clawRightLower2, ModelTransform.NONE);
		leftArm.addChild("claw_left_upper1", clawLeftUpper1, ModelTransform.NONE);
		leftArm.addChild("claw_left_upper2", clawLeftUpper2, ModelTransform.NONE);
		leftArm.addChild("claw_left_lower1", clawLeftLower1, ModelTransform.NONE);
		leftArm.addChild("claw_left_lower2", clawLeftLower2, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
