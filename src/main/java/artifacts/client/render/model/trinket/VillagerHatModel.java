package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class VillagerHatModel extends BipedEntityModel<LivingEntity> {

	public VillagerHatModel(ModelPart root) {
		super(root);

		setVisible(false);
		head.visible = true;
	}

	public static TexturedModelData getTexturedGloveData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder brim = ModelPartBuilder.create().uv(0, 16).cuboid(-8, -5.125F, -8, 16, 0, 16);

		root.getChild("head").addChild("brim", brim, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 32);
	}
}
