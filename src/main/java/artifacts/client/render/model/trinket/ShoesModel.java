package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class ShoesModel extends BipedEntityModel<LivingEntity> {

	public ShoesModel(float delta) {
		super(0, 0, 32, 32);

		leftLeg = new ModelPart(this, 0, 0);
		rightLeg = new ModelPart(this, 16, 0);

		leftLeg.addCuboid(-2, 0, -2, 4, 12, 4, delta);
		rightLeg.addCuboid(-2, 0, -2, 4, 12, 4, delta);
		leftLeg.setTextureOffset(0, 16);
		rightLeg.setTextureOffset(16, 16);
		leftLeg.addCuboid(-2, 12 - 3 + delta * 3 / 4, -3F - delta * 5 / 4, 4, 3, 1, delta, delta / 4, delta / 4);
		rightLeg.addCuboid(-2, 12 - 3 + delta * 3 / 4, -3F - delta * 5 / 4, 4, 3, 1, delta, delta / 4, delta / 4);

		setVisible(false);
		leftLeg.visible = rightLeg.visible = true;
	}
}
