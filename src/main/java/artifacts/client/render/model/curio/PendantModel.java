package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class PendantModel extends BipedEntityModel<LivingEntity> {

	public PendantModel() {
		super(RenderLayer::getEntityTranslucent, 0, 0, 64, 64);

		setVisible(false);

		torso = new ModelPart(this, 0, 0);
		ModelPart gem = new ModelPart(this, 50, 0);

		torso.addCuboid(-(2 * 8) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8, 2 * 12 + 1, 2 * 4 + 1);
		gem.addCuboid(-1, 4.5F, -5, 2, 2, 1);

		torso.addChild(gem);
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		torso.render(matrixStack, buffer, light, overlay, red, green, blue, alpha);
	}
}
