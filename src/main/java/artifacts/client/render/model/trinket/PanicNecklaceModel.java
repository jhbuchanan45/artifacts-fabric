package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class PanicNecklaceModel extends BipedEntityModel<LivingEntity> {

	public PanicNecklaceModel(ModelPart root) {
		super(root, RenderLayer::getEntityTranslucent);

		setVisible(false);
	}

	public static TexturedModelData getTexturedGloveData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 0).cuboid(-(2 * 8 + 1) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8 + 1, 2 * 12 + 1, 2 * 4 + 1);
		ModelPartBuilder gem1 = ModelPartBuilder.create().uv(52, 0).cuboid(-2.5F, 5.5F, -5, 2, 2, 1);
		ModelPartBuilder gem2 = ModelPartBuilder.create().uv(58, 0).cuboid(0.5F, 5.5F, -5, 2, 2, 1);
		ModelPartBuilder gem3 = ModelPartBuilder.create().uv(52, 3).cuboid(-1.5F, 6.5F, -5, 3, 2, 1);
		ModelPartBuilder gem4 = ModelPartBuilder.create().uv(60, 4).cuboid(-0.5F, 8.5F, -5, 1, 1, 1);

		ModelPartData bodyData = root.addChild("body", body, ModelTransform.NONE);
		bodyData.addChild("gem1", gem1, ModelTransform.NONE);
		bodyData.addChild("gem2", gem2, ModelTransform.NONE);
		bodyData.addChild("gem3", gem3, ModelTransform.NONE);
		bodyData.addChild("gem4", gem4, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		body.render(matrixStack, buffer, light, overlay, red, green, blue, alpha);
	}
}
