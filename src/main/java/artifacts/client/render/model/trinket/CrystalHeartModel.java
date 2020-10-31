package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class CrystalHeartModel extends BipedEntityModel<LivingEntity> {

	public CrystalHeartModel() {
		super(RenderLayer::getEntityTranslucent, 0.5F, 0, 32, 32);

		torso = new ModelPart(this, 0, 0);
		ModelPart heart1 = new ModelPart(this, 0, 16);
		ModelPart heart2 = new ModelPart(this, 6, 16);
		ModelPart heart3 = new ModelPart(this, 0, 20);
		ModelPart heart4 = new ModelPart(this, 4, 20);
		ModelPart heart5 = new ModelPart(this, 8, 20);

		torso.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		heart1.addCuboid(-2.5F, 0, 0, 2, 3, 1);
		heart2.addCuboid(0.5F, 0, 0, 2, 3, 1);
		heart3.addCuboid(-0.5F, 1, 0, 1, 4, 1);
		heart4.addCuboid(-1.5F, 3, 0, 1, 1, 1);
		heart5.addCuboid(0.5F, 3, 0, 1, 1, 1);

		heart1.setPivot(2.5F, 9, -3);

		heart1.addChild(heart2);
		heart1.addChild(heart3);
		heart1.addChild(heart4);
		heart1.addChild(heart5);
		torso.addChild(heart1);

		setVisible(false);
		torso.visible = true;
	}
}
