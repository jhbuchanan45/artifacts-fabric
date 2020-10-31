package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class VillagerHatModel extends BipedEntityModel<LivingEntity> {

	public VillagerHatModel() {
		super(0.5F, 0, 32, 32);
		ModelPart brim = new ModelPart(this, 0, 16);
		brim.addCuboid(-8, -5.125F, -8, 16, 0, 16);
		head.addChild(brim);
		setVisible(false);
		head.visible = true;
	}
}
