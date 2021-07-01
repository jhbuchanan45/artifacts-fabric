package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.entity.LivingEntity;

public class ObsidianSkullModel extends BipedEntityModel<LivingEntity> {

	public ObsidianSkullModel(ModelPart root) {
		super(root);

		setVisible(false);
		body.visible = true;
	}

	public static TexturedModelData getTexturedGloveData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 0).cuboid(-4, 0, -2, 8, 12, 4, new Dilation(0.5F));
		ModelPartBuilder skull = ModelPartBuilder.create().uv(0, 16).cuboid(-2.5F, 0, 0, 5, 3, 4);
		ModelPartBuilder tooth1 = ModelPartBuilder.create().uv(18, 16).cuboid(-1.5F, 3, 0, 1, 1, 2);
		ModelPartBuilder tooth2 = ModelPartBuilder.create().uv(18, 19).cuboid(0.5F, 3, 0, 1, 1, 2);

		ModelPartData skullData = root.addChild("body", body, ModelTransform.NONE)
		.addChild("skull", skull, ModelTransform.of(4.5F, 9, -4, 0, -0.5F, 0));
		skullData.addChild("tooth1", tooth1, ModelTransform.NONE);
		skullData.addChild("tooth2", tooth2, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 32);
	}
}
