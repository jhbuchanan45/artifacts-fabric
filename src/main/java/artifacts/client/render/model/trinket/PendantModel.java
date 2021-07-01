package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class PendantModel extends BipedEntityModel<LivingEntity> {

	public PendantModel(ModelPart root) {
		super(root, RenderLayer::getEntityTranslucent);

		setVisible(false);
	}

	public static ModelData getTexturedModelData(Dilation dilation) {
		ModelData modelData = BipedEntityModel.getModelData(dilation, 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 0).cuboid(-(2 * 8) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8, 2 * 12 + 1, 2 * 4 + 1);
		ModelPartBuilder gem = ModelPartBuilder.create().uv(50, 0).cuboid(-1, 4.5F, -5, 2, 2, 1);

		root.addChild("body", body, ModelTransform.NONE);
		root.getChild("body").addChild("gem", gem, ModelTransform.NONE);

		return modelData;
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		body.render(matrixStack, buffer, light, overlay, red, green, blue, alpha);
	}
}
