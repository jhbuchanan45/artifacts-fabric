package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class SuperstitiousHatModel extends BipedEntityModel<LivingEntity> {

	public SuperstitiousHatModel(ModelPart root) {
		super(root);
		setVisible(false);
		head.visible = true;
	}

	public static TexturedModelData getTexturedGloveData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder head = ModelPartBuilder.create().uv(0, 0).cuboid(-4, -16, -4, 8, 8, 8);
		ModelPartBuilder brim = ModelPartBuilder.create().uv(0, 16).cuboid(-5, -9, -5, 10, 1, 10);

		root.addChild("head", head, ModelTransform.NONE)
				.addChild("brim", brim, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 32);
	}
}
