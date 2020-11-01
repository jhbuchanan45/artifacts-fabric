package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class RunningShoesModel extends BipedEntityModel<LivingEntity> {

	public RunningShoesModel() {
		super(1, 0, 32, 32);

		leftLeg = new ModelPart(this, 0, 0);
		rightLeg = new ModelPart(this, 16, 0);
		ModelPart leftShoeTip = new ModelPart(this, 0, 16);
		ModelPart rightShoeTip = new ModelPart(this, 16, 16);

		leftLeg.addCuboid(-2, 0, -2, 4, 12, 4, 0.5F);
		rightLeg.addCuboid(-2, 0, -2, 4, 12, 4, 0.5F);
		leftShoeTip.addCuboid(-2, 9.375F, -3.625F, 4, 3, 1, 0.5F, 0.125F, 0.125F);
		rightShoeTip.addCuboid(-2, 9.375F, -3.625F, 4, 3, 1, 0.5F, 0.125F, 0.125F);

		leftLeg.addChild(leftShoeTip);
		rightLeg.addChild(rightShoeTip);

		setVisible(false);
		leftLeg.visible = rightLeg.visible = true;
	}
}
