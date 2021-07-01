package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class CrossNecklaceModel extends BipedEntityModel<LivingEntity> {

	public CrossNecklaceModel(ModelPart root) {
		super(root);

		setVisible(false);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 0).cuboid(-(2 * 8 + 1) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8 + 1, 2 * 12 + 1, 2 * 4 + 1);
		ModelPartBuilder cross1 = ModelPartBuilder.create().uv(52, 0).cuboid(-0.5F, 4.5F, -5, 1, 4, 1);
		ModelPartBuilder cross2 = ModelPartBuilder.create().uv(56, 0).cuboid(-1.5F, 5.5F, -5, 1, 1, 1);
		ModelPartBuilder cross3 = ModelPartBuilder.create().uv(60, 0).cuboid(0.5F, 5.5F, -5, 1, 1, 1);

		root.addChild("body", body, ModelTransform.NONE);
		ModelPartData bodyData = root.getChild("body");
		bodyData.addChild("cross1", cross1, ModelTransform.NONE);
		bodyData.addChild("cross2", cross2, ModelTransform.NONE);
		bodyData.addChild("cross3", cross3, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		body.render(matrixStack, buffer, light, overlay, red, green, blue, alpha);
	}
}
