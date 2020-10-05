package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class BunnyHoppersModel extends BipedEntityModel<LivingEntity> {

	public BunnyHoppersModel() {
		super(0.51F);
		ModelPart head1 = new ModelPart(this, 0, 0);
		ModelPart head2 = new ModelPart(this, 32, 0);
		ModelPart earLeft1 = new ModelPart(this, 20, 0);
		ModelPart earLeft2 = new ModelPart(this, 52, 0);
		ModelPart earRight1 = new ModelPart(this, 26, 0);
		ModelPart earRight2 = new ModelPart(this, 58, 0);
		ModelPart bipedLeftLegwear = new ModelPart(this, 16, 16);
		ModelPart bipedRightLegwear = new ModelPart(this, 48, 16);
		ModelPart nose1 = new ModelPart(this, 0, 9);
		ModelPart nose2 = new ModelPart(this, 32, 9);
		ModelPart tail1 = new ModelPart(this, 20, 6);
		ModelPart tail2 = new ModelPart(this, 52, 6);

		head1.addCuboid(-2.5F, 8.51F, -7.01F, 5, 4, 5);
		head2.addCuboid(-2.5F, 8.51F, -7, 5, 4, 5);
		earRight1.addCuboid(1.15F, 3.51F, -3.01F, 2, 5, 1);
		earRight2.addCuboid(1.15F, 3.51F, -3, 2, 5, 1);
		earRight1.yaw = 0.2617994F;
		earRight2.yaw = 0.2617994F;
		earLeft1.addCuboid(-3.15F, 3.51F, -3.01F, 2, 5, 1);
		earLeft2.addCuboid(-3.15F, 3.51F, -3, 2, 5, 1);
		earLeft1.yaw = -0.2617994F;
		earLeft2.yaw = -0.2617994F;
		bipedLeftLegwear.addCuboid(-2, 0, -2, 4, 12, 4, 0.75F);
		bipedRightLegwear.addCuboid(-2, 0, -2, 4, 12, 4, 0.75F);
		nose1.addCuboid(-0.5F, 10, -7.5F, 1, 1, 1);
		nose2.addCuboid(-0.5F, 10, -7.5F, 1, 1, 1);
		tail1.addCuboid(-1, 9, 2, 2, 2, 2);
		tail2.addCuboid(-1, 9, 2, 2, 2, 2);

		leftLeg.addChild(head1);
		rightLeg.addChild(head2);
		leftLeg.addChild(earRight1);
		rightLeg.addChild(earRight2);
		leftLeg.addChild(earLeft1);
		rightLeg.addChild(earLeft2);
		leftLeg.addChild(bipedLeftLegwear);
		rightLeg.addChild(bipedRightLegwear);
		leftLeg.addChild(nose1);
		rightLeg.addChild(nose2);
		leftLeg.addChild(tail1);
		rightLeg.addChild(tail2);

		setVisible(false);
		leftLeg.visible = rightLeg.visible = true;
	}
}
