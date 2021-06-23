package artifacts.client.render.model.trinket;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class PanicNecklaceModel extends BipedEntityModel<LivingEntity> {

	public PanicNecklaceModel() {
		super(RenderLayer::getEntityTranslucent, 0, 0, 64, 64);

		setVisible(false);

		body = new ModelPart(this, 0, 0);
		ModelPart gem1 = new ModelPart(this, 52, 0);
		ModelPart gem2 = new ModelPart(this, 58, 0);
		ModelPart gem3 = new ModelPart(this, 52, 3);
		ModelPart gem4 = new ModelPart(this, 60, 4);

		body.addCuboid(-(2 * 8 + 1) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8 + 1, 2 * 12 + 1, 2 * 4 + 1);
		gem1.addCuboid(-2.5F, 5.5F, -5, 2, 2, 1);
		gem2.addCuboid(0.5F, 5.5F, -5, 2, 2, 1);
		gem3.addCuboid(-1.5F, 6.5F, -5, 3, 2, 1);
		gem4.addCuboid(-0.5F, 8.5F, -5, 1, 1, 1);

		body.addChild(gem1);
		body.addChild(gem2);
		body.addChild(gem3);
		body.addChild(gem4);
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		body.render(matrixStack, buffer, light, overlay, red, green, blue, alpha);
	}
}
