package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;

public class GoldenHookModel extends GloveModel {
	
	public GoldenHookModel(ModelPart root, boolean smallArms) {
		super(root, smallArms);
	}

	public static TexturedModelData getTexturedGloveData(Dilation dilation, boolean smallArms) {
		ModelData modelData = GloveModel.getTexturedModelData(dilation, smallArms);
		ModelPartData root = modelData.getRoot();
		ModelPartData rightArm = root.getChild("right_arm");
		ModelPartData leftArm = root.getChild("left_arm");

		ModelPartBuilder hookRight = ModelPartBuilder.create().uv(0, 0).cuboid(smallArms ? -3 : -3.5F, 12, -0.5F, 5, 5, 1);
		ModelPartBuilder hookBaseRight = ModelPartBuilder.create().uv(0, 0).cuboid(smallArms ? -3 : -3.5F, 12, -0.5F, 5, 5, 1);
		ModelPartBuilder hookLeft = ModelPartBuilder.create().uv(0, 0).cuboid(smallArms ? -3 : -3.5F, 12, -0.5F, 5, 5, 1);
		ModelPartBuilder hookBaseLeft = ModelPartBuilder.create().uv(0, 0).cuboid(smallArms ? -3 : -3.5F, 12, -0.5F, 5, 5, 1);

		rightArm.addChild("hook_right", hookRight, ModelTransform.NONE);
		rightArm.addChild("hook_base_right", hookBaseRight, ModelTransform.NONE);
		leftArm.addChild("hook_left", hookLeft, ModelTransform.NONE);
		leftArm.addChild("hook_base_left", hookBaseLeft, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
