package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class FlippersModel extends BipedEntityModel<LivingEntity> {

	public FlippersModel() {
		super(0.5F, 0, 64, 96);

		setVisible(false);
		leftLeg.visible = true;
		rightLeg.visible = true;

		ModelPart flipperLeft = new ModelPart(this, 0, 32);
		ModelPart flipperRight = new ModelPart(this, 0, 52);
		flipperLeft.addCuboid(-2, 11.5F, -16, 9, 1, 20);
		flipperRight.addCuboid(-7, 11.5F, -16, 9, 1, 20);
		leftLeg.addChild(flipperLeft);
		rightLeg.addChild(flipperRight);
	}
}
