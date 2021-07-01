package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class BunnyHoppersModel extends BipedEntityModel<LivingEntity> {

	public BunnyHoppersModel(ModelPart root) {
		super(root);

		setVisible(false);
		leftLeg.visible = rightLeg.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.51F), 0);
		ModelPartData root = modelData.getRoot();
		ModelPartData leftLeg = root.getChild("left_leg");
		ModelPartData rightLeg = root.getChild("right_leg");

		ModelPartBuilder head1 = ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, 8.51F, -7.01F, 5, 4, 5);
		ModelPartBuilder head2 = ModelPartBuilder.create().uv(32, 0).cuboid(-2.5F, 8.51F, -7, 5, 4, 5);
		ModelPartBuilder earLeft1 = ModelPartBuilder.create().uv(20, 0).cuboid(1.15F, 3.51F, -3.01F, 2, 5, 1);
		ModelPartBuilder earLeft2 = ModelPartBuilder.create().uv(52, 0).cuboid(1.15F, 3.51F, -3, 2, 5, 1);
		ModelPartBuilder earRight1 = ModelPartBuilder.create().uv(26, 0).cuboid(-3.15F, 3.51F, -3.01F, 2, 5, 1);
		ModelPartBuilder earRight2 = ModelPartBuilder.create().uv(58, 0).cuboid(-3.15F, 3.51F, -3, 2, 5, 1);
		ModelPartBuilder bipedLeftLegwear = ModelPartBuilder.create().uv(16, 16).cuboid(-2, 0, -2, 4, 12, 4, new Dilation(0.75F));
		ModelPartBuilder bipedRightLegwear = ModelPartBuilder.create().uv(48, 16).cuboid(-2, 0, -2, 4, 12, 4, new Dilation(0.75F));
		ModelPartBuilder nose1 = ModelPartBuilder.create().uv(0, 9).cuboid(-0.5F, 10, -7.5F, 1, 1, 1);
		ModelPartBuilder nose2 = ModelPartBuilder.create().uv(32, 9).cuboid(-0.5F, 10, -7.5F, 1, 1, 1);
		ModelPartBuilder tail1 = ModelPartBuilder.create().uv(20, 6).cuboid(-1, 9, 2, 2, 2, 2);
		ModelPartBuilder tail2 = ModelPartBuilder.create().uv(52, 6).cuboid(-1, 9, 2, 2, 2, 2);

		leftLeg.addChild("head1", head1, ModelTransform.NONE);
		rightLeg.addChild("head2", head2, ModelTransform.NONE);
		leftLeg.addChild("ear_right1", earRight1, ModelTransform.rotation(0, 0.2617994F, 0));
		rightLeg.addChild("ear_right2", earRight2, ModelTransform.rotation(0, 0.2617994F, 0));
		leftLeg.addChild("ear_left1", earLeft1, ModelTransform.rotation(0, -0.2617994F, 0));
		rightLeg.addChild("ear_left2", earLeft2, ModelTransform.rotation(0, -0.2617994F, 0));
		leftLeg.addChild("biped_left_legwear", bipedLeftLegwear, ModelTransform.NONE);
		rightLeg.addChild("biped_right_legwear", bipedRightLegwear, ModelTransform.NONE);
		leftLeg.addChild("nose1", nose1, ModelTransform.NONE);
		rightLeg.addChild("nose2", nose2, ModelTransform.NONE);
		leftLeg.addChild("tail1", tail1, ModelTransform.NONE);
		rightLeg.addChild("tail2", tail2, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 32);
	}
}
