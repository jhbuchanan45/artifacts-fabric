package artifacts.client.render;

import artifacts.client.render.model.entity.MimicChestLayerModel;
import artifacts.client.render.model.entity.MimicModel;
import artifacts.entity.MimicEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;

import java.util.Calendar;

@Environment(EnvType.CLIENT)
public class MimicChestLayer extends FeatureRenderer<MimicEntity, MimicModel> {

	private final MimicChestLayerModel model;
	private boolean isChristmas;

	public MimicChestLayer(FeatureRendererContext<MimicEntity, MimicModel> entityRenderer) {
		super(entityRenderer);

		model = new MimicChestLayerModel();

		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
			isChristmas = true;
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, MimicEntity mimic, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if (!mimic.isInvisible()) {
			matrices.push();

			matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(180));
			matrices.translate(-0.5, -1.5, -0.5);

			getContextModel().copyStateTo(model);
			model.animateModel(mimic, limbAngle, limbDistance, tickDelta);
			model.setAngles(mimic, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
			VertexConsumer builder = getChestMaterial().getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
			model.render(matrices, builder, light, LivingEntityRenderer.getOverlay(mimic, 0), 1, 1, 1, 1);

			matrices.pop();
		}
	}

	private SpriteIdentifier getChestMaterial() {
		return TexturedRenderLayers.getChestTexture(null, ChestType.SINGLE, isChristmas);
	}
}
