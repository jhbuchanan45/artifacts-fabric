package artifacts.client.render.model.trinket;


import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;

public class SteadfastSpikesModel extends PlayerEntityModel<LivingEntity> {

	public SteadfastSpikesModel(ModelPart root, boolean thinArms) {
		super(root, false);

		setVisible(false);
		leftLeg.visible = true;
		rightLeg.visible = true;
		leftPants.visible = true;
		rightPants.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = PlayerEntityModel.getTexturedModelData(new Dilation(0.5F), false);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder clawLeft1 = ModelPartBuilder.create().uv(32, 0).cuboid(-1.5F, 9, -7, 1, 3, 5);
		ModelPartBuilder clawRight1 = ModelPartBuilder.create().uv(43, 0).cuboid(-1.5F, 9, -7, 1, 3, 5);
		ModelPartBuilder clawLeft2 = ModelPartBuilder.create().uv(32, 8).cuboid(0.5F, 9, -7, 1, 3, 5);
		ModelPartBuilder clawRight2 = ModelPartBuilder.create().uv(43, 8).cuboid(0.5F, 9, -7, 1, 3, 5);

		ModelPartData leftLegData = root.getChild("left_leg");
		ModelPartData rightLegData = root.getChild("right_leg");
		leftLegData.addChild("claw_left1", clawLeft1, ModelTransform.NONE);
		rightLegData.addChild("claw_right1", clawRight1, ModelTransform.NONE);
		leftLegData.addChild("claw_left2", clawLeft2, ModelTransform.NONE);
		rightLegData.addChild("claw_right2", clawRight2, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
