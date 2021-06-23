package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class NightVisionGogglesModel extends BipedEntityModel<LivingEntity> {

	public NightVisionGogglesModel() {
		super(0.5F, 0, 64, 64);

		setVisible(false);
		head.visible = true;
		hat.visible = true;

		ModelPart goggles = new ModelPart(this, 0, 37);
		ModelPart eyeLeft = new ModelPart(this, 0, 32);
		ModelPart eyeRight = new ModelPart(this, 10, 32);

		goggles.addCuboid(-4, -6, -5 + 0.05F, 8, 4, 1);
		eyeLeft.addCuboid(1.5F, -5, -8 + 0.05F, 2, 2, 3);
		eyeRight.addCuboid(-3.5F, -5, -8 + 0.05F, 2, 2, 3);

		head.addChild(goggles);
		head.addChild(eyeLeft);
		head.addChild(eyeRight);
	}
}
