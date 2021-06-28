package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class SnorkelModel extends BipedEntityModel<LivingEntity> {

	public SnorkelModel() {
		super(RenderLayer::getEntityTranslucent, 0.5F, 0, 64, 64);

		setVisible(false);
		head.visible = true;
		hat.visible = true;

		ModelPart snorkelMouthPiece = new ModelPart(this, 0, 46);
		ModelPart snorkelTube = new ModelPart(this, 0, 32);

		snorkelMouthPiece.addCuboid(-2, -1.5F, -6, 8, 2, 2);
		snorkelTube.addCuboid(4.01F, -5, -3, 2, 2, 12);

		head.addChild(snorkelMouthPiece);
		head.addChild(snorkelTube);

		snorkelTube.pitch = 0.7853F;
	}
}
