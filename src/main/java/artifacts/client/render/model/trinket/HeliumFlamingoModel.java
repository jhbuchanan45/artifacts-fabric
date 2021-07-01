package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class HeliumFlamingoModel extends BipedEntityModel<LivingEntity> {

	public HeliumFlamingoModel(ModelPart root) {
		super(root);

		setVisible(false);
		body.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create();
		ModelPartBuilder bone = ModelPartBuilder.create()
				.cuboid(null,-1, 1, -14, 2, 3, 5, 16, 36)
				.cuboid(null,4, 9, -7, 4, 4, 14, 0, 18)
				.cuboid(null,-8, 9, -7, 4, 4, 14, 0, 0)
				.cuboid(null,-4, 9, 3, 8, 4, 4, 36, 0)
				.cuboid(null,-4, 9, -7, 8, 4, 4, 36, 8)
				.cuboid(null,-2, 1, -9, 4, 11, 4, 0, 36);

		root.addChild("body", body, ModelTransform.pivot(0, 0, 0))
		.addChild("bone", bone, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
