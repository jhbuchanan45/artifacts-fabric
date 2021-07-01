package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class CloudInABottleModel extends BipedEntityModel<LivingEntity> {

	protected final ModelPart cloud;

	public CloudInABottleModel(ModelPart root) {
		super(root, RenderLayer::getEntityTranslucent);
		cloud = root.getChild("cloud");

		setVisible(false);
		body.visible = true;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 0).cuboid(-4, 0, -2, 8, 12, 4, new Dilation(0.5F));
		ModelPartBuilder jar = ModelPartBuilder.create().uv(0, 16).cuboid(-2, 0, -2, 4, 5, 4);
		ModelPartBuilder lid = ModelPartBuilder.create().uv(0, 25).cuboid(-1, -1, -1, 2, 1, 2);
		ModelPartBuilder cloud = ModelPartBuilder.create().uv(8, 25).cuboid(-1, 1.5F, -1, 2, 2, 2);

		root.addChild("body", body, ModelTransform.NONE);
		root.getChild("body").addChild("jar", jar, ModelTransform.of(4, 9, -3, 0, -0.5F, 0));
		ModelPartData jarData = root.getChild("body").getChild("jar");
		jarData.addChild("lid", lid, ModelTransform.NONE);
		jarData.addChild("cloud", cloud, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		cloud.yaw = (animationProgress) / 50;
		cloud.pivotY = MathHelper.cos((animationProgress) / 30) / 2;
	}
}
