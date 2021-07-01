package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class WhoopeeCushionModel extends BipedEntityModel<LivingEntity> {

	public WhoopeeCushionModel(ModelPart root) {
		super(root);

		setVisible(false);
		head.visible = true;
	}

	public static TexturedModelData getTexturedGloveData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder head = ModelPartBuilder.create().cuboid(-3, -10, -3, 6, 2, 6)
				.cuboid(null, -2, -9.25F, 3, 4, 0, 4, Dilation.NONE, 0, 8);

		root.addChild("head", head, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 16);
	}
}
