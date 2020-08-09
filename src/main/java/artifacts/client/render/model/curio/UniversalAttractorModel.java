package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class UniversalAttractorModel extends BipedEntityModel<LivingEntity> {

	public UniversalAttractorModel() {
		super(0.5F, 0, 32, 32);

		torso = new ModelPart(this, 0, 0);
		ModelPart magnet = new ModelPart(this, 0, 16);

		ModelPart magnet1 = new ModelPart(this, 0, 19);
		ModelPart magnet2 = new ModelPart(this, 6, 19);

		torso.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		magnet.addCuboid(0, 9, -3, 5, 2, 1);
		magnet1.addCuboid(0, 11, -3, 2, 4, 1);
		magnet2.addCuboid(3, 11, -3, 2, 4, 1);

		magnet.addChild(magnet1);
		magnet.addChild(magnet2);
		torso.addChild(magnet);

		setVisible(false);
		torso.visible = true;
	}
}
