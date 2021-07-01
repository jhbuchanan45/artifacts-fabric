package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class KittySlippersModel extends BipedEntityModel<LivingEntity> {

	public KittySlippersModel(ModelPart root) {
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
		ModelPartBuilder earLeft1 = ModelPartBuilder.create().uv(0, 9).cuboid(-2, 7.51F, -4, 1, 1, 2);
		ModelPartBuilder earLeft2 = ModelPartBuilder.create().uv(32, 9).cuboid(-2, 7.51F, -4, 1, 1, 2);
		ModelPartBuilder earRight1 = ModelPartBuilder.create().uv(0, 9).cuboid(1, 7.51F, -4, 1, 1, 2);
		ModelPartBuilder earRight2 = ModelPartBuilder.create().uv(32, 9).cuboid(1, 7.51F, -4, 1, 1, 2);
		ModelPartBuilder nose1 = ModelPartBuilder.create().uv(12, 9).cuboid(-1.5F, 10.51F, -8, 3, 2, 1);
		ModelPartBuilder nose2 = ModelPartBuilder.create().uv(44, 9).cuboid(-1.5F, 10.51F, -8, 3, 2, 1);
		ModelPartBuilder bipedLeftLegwear = ModelPartBuilder.create().uv(16, 16).cuboid(-2, 0, -2, 4, 12, 4, new Dilation(0.75F));
		ModelPartBuilder bipedRightLegwear = ModelPartBuilder.create().uv(48, 16).cuboid(-2, 0, -2, 4, 12, 4, new Dilation(0.75F));

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

		return TexturedModelData.of(modelData, 64, 32);
	}
}
