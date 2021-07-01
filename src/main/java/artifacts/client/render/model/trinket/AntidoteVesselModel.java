package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.entity.LivingEntity;

public class AntidoteVesselModel extends BipedEntityModel<LivingEntity> {

	public AntidoteVesselModel(ModelPart root) {
		super(root);

		setVisible(false);

		root.getChild("body").visible = true;
		root.getChild("body").getChild("jar").yaw = -0.5F;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 0).cuboid(-4, 0, -2, 8, 12, 4, new Dilation(0.5F));
		ModelPartBuilder jar = ModelPartBuilder.create().uv(0, 16).cuboid(-1, 4.5F, -5, 2, 2, 1);
		ModelPartBuilder lid = ModelPartBuilder.create().uv(0, 26).cuboid(-1, -1, -1, 2, 1, 2);

		root.addChild("body", body, ModelTransform.NONE);
		ModelPartData bodyData = root.getChild("body");
		bodyData.addChild("jar", jar, ModelTransform.pivot(4, 9, -3));
		ModelPartData jarData = bodyData.getChild("jar");
		jarData.addChild("lid", lid, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 32);
	}
}
