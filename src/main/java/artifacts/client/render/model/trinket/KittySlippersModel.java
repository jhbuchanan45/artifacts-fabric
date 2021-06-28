package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class KittySlippersModel extends BipedEntityModel<LivingEntity> {

	public KittySlippersModel() {
		super(0.51F);
		ModelPart head1 = new ModelPart(this, 0, 0);
		ModelPart head2 = new ModelPart(this, 32, 0);
		ModelPart earLeft1 = new ModelPart(this, 0, 9);
		ModelPart earLeft2 = new ModelPart(this, 32, 9);
		ModelPart earRight1 = new ModelPart(this, 0, 9);
		ModelPart earRight2 = new ModelPart(this, 32, 9);
		ModelPart nose1 = new ModelPart(this, 12, 9);
		ModelPart nose2 = new ModelPart(this, 44, 9);
		ModelPart bipedLeftLegwear = new ModelPart(this, 16, 16);
		ModelPart bipedRightLegwear = new ModelPart(this, 48, 16);

		head1.addCuboid(-2.5F, 8.51F, -7.01F, 5, 4, 5);
		head2.addCuboid(-2.5F, 8.51F, -7, 5, 4, 5);
		earLeft1.addCuboid(-2, 7.51F, -4, 1, 1, 2);
		earLeft2.addCuboid(-2, 7.51F, -4, 1, 1, 2);
		earRight1.addCuboid(1, 7.51F, -4, 1, 1, 2);
		earRight2.addCuboid(1, 7.51F, -4, 1, 1, 2);
		nose1.addCuboid(-1.5F, 10.51F, -8, 3, 2, 1);
		nose2.addCuboid(-1.5F, 10.51F, -8, 3, 2, 1);
		bipedLeftLegwear.addCuboid(-2, 0, -2, 4, 12, 4, 0.75F);
		bipedRightLegwear.addCuboid(-2, 0, -2, 4, 12, 4, 0.75F);

		leftLeg.addChild(head1);
		rightLeg.addChild(head2);
		leftLeg.addChild(nose1);
		rightLeg.addChild(nose2);
		leftLeg.addChild(earLeft1);
		leftLeg.addChild(earRight1);
		rightLeg.addChild(earLeft2);
		rightLeg.addChild(earRight2);
		leftLeg.addChild(bipedLeftLegwear);
		rightLeg.addChild(bipedRightLegwear);

		setVisible(false);
		leftLeg.visible = rightLeg.visible = true;
	}
}
