package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class ObsidianSkullModel extends BipedEntityModel<LivingEntity> {

	public ObsidianSkullModel() {
		super(0.5F, 0, 32, 32);

		torso = new ModelPart(this, 0, 0);
		ModelPart skull = new ModelPart(this, 0, 16);

		ModelPart tooth1 = new ModelPart(this, 18, 16);
		ModelPart tooth2 = new ModelPart(this, 18, 19);

		torso.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		skull.addCuboid(-2.5F, 0, 0, 5, 3, 4);
		tooth1.addCuboid(-1.5F, 3, 0, 1, 1, 2);
		tooth2.addCuboid(0.5F, 3, 0, 1, 1, 2);
		skull.setPivot(4.5F, 9, -4);

		skull.yaw = -0.5F;

		skull.addChild(tooth1);
		skull.addChild(tooth2);

		torso.addChild(skull);

		setVisible(false);
		torso.visible = true;
	}
}
