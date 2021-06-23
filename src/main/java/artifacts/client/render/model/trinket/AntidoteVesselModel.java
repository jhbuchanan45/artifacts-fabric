package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class AntidoteVesselModel extends BipedEntityModel<LivingEntity> {

	public AntidoteVesselModel() {
		super(0.5F, 0, 32, 32);

		body = new ModelPart(this, 0, 0);
		ModelPart jar = new ModelPart(this, 0, 16);
		ModelPart lid = new ModelPart(this, 0, 26);

		body.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		jar.addCuboid(-2, 0, -2, 4, 6, 4);
		lid.addCuboid(-1, -1, -1, 2, 1, 2);
		jar.setPivot(4, 9, -3);
		jar.yaw = -0.5F;

		jar.addChild(lid);
		body.addChild(jar);

		setVisible(false);
		body.visible = true;
	}
}
