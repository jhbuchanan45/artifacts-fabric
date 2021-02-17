package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class HeliumFlamingoModel extends BipedEntityModel<LivingEntity> {

	public HeliumFlamingoModel() {
		super(0, 0, 64, 64);
		torso = new ModelPart(this);

		ModelPart bone = new ModelPart(this);
		bone.setPivot(0, 0, 0);
		bone.setTextureOffset(16, 36).addCuboid(-1, 1, -14, 2, 3, 5);
		bone.setTextureOffset(0, 18).addCuboid(4, 9, -7, 4, 4, 14);
		bone.setTextureOffset(0, 0).addCuboid(-8, 9, -7, 4, 4, 14);
		bone.setTextureOffset(36, 0).addCuboid(-4, 9, 3, 8, 4, 4);
		bone.setTextureOffset(36, 8).addCuboid(-4, 9, -7, 8, 4, 4);
		bone.setTextureOffset(0, 36).addCuboid(-2, 1, -9, 4, 11, 4);

		torso.addChild(bone);

		setVisible(false);
		torso.visible = true;
	}
}
