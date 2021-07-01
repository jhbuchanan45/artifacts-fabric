package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class SnorkelModel extends BipedEntityModel<LivingEntity> {

	public SnorkelModel(ModelPart root) {
		super(root, RenderLayer::getEntityTranslucent);

		setVisible(false);
		head.visible = true;
		hat.visible = true;
	}

	public static TexturedModelData getTexturedGloveData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder snorkelMouthPiece = ModelPartBuilder.create().uv(0, 46).cuboid(-2, -1.5F, -6, 8, 2, 2);
		ModelPartBuilder snorkelTube = ModelPartBuilder.create().uv(0, 32).cuboid(4.01F, -5, -3, 2, 2, 12);

		ModelPartData headData = root.getChild("head");
		headData.addChild("snorkel_mouth_piece", snorkelMouthPiece, ModelTransform.NONE);
		headData.addChild("snorkel_tube", snorkelTube, ModelTransform.rotation(0.7853F, 0, 0));

		return TexturedModelData.of(modelData, 64, 64);
	}
}
