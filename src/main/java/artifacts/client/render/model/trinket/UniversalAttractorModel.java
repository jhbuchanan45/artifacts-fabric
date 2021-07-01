package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class UniversalAttractorModel extends BipedEntityModel<LivingEntity> {

	public UniversalAttractorModel(ModelPart root) {
		super(root);

		setVisible(false);
		body.visible = true;
	}

	public static TexturedModelData getTexturedGloveData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 0).cuboid(-4, 0, -2, 8, 12, 4, new Dilation(0.5F));
		ModelPartBuilder magnet = ModelPartBuilder.create().uv(0, 16).cuboid(0, 9, -3, 5, 2, 1);
		ModelPartBuilder magnet1 = ModelPartBuilder.create().uv(0, 19).cuboid(0, 11, -3, 2, 4, 1);
		ModelPartBuilder magnet2 = ModelPartBuilder.create().uv(6, 19).cuboid(3, 11, -3, 2, 4, 1);

		ModelPartData magnetData = root.addChild("body", body, ModelTransform.NONE)
				.addChild("magnet", magnet, ModelTransform.NONE);
		magnetData.addChild("magnet1", magnet1, ModelTransform.NONE);
		magnetData.addChild("magnet2", magnet2, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 32);
	}
}
