package artifacts.common.item;

import artifacts.client.render.model.curio.GloveModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public abstract class RenderableGloveCurio extends RenderableCurio {

	protected Object modelDefault;
	protected Object modelSlim;

	@Environment(EnvType.CLIENT)
	protected static boolean hasSmallArms(Entity entity) {
		return entity instanceof AbstractClientPlayerEntity && ((AbstractClientPlayerEntity) entity).getModel().equals("slim");
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void render(String identifier, int index, MatrixStack matrixStack, VertexConsumerProvider renderTypeBuffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean smallArms = hasSmallArms(entity);
		GloveModel model = getModel(smallArms);
		model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		model.animateModel(entity, limbSwing, limbSwingAmount, partialTicks);
		RenderHelper.followBodyRotations(entity, model);
		VertexConsumer vertexBuilder = ItemRenderer.getArmorVertexConsumer(renderTypeBuffer, model.getLayer(getTexture(smallArms)), false, false);
		model.renderHand(index == 0, matrixStack, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected GloveModel getModel() {
		if (modelDefault == null) {
			modelDefault = new GloveModel(false);
		}
		return (GloveModel) modelDefault;
	}

	@Environment(EnvType.CLIENT)
	protected GloveModel getSlimModel() {
		if (modelSlim == null) {
			modelSlim = new GloveModel(true);
		}
		return (GloveModel) modelSlim;
	}

	@Environment(EnvType.CLIENT)
	protected GloveModel getModel(boolean smallArms) {
		return (smallArms ? getSlimModel() : getModel());
	}

	@Environment(EnvType.CLIENT)
	protected Identifier getTexture(boolean smallArms) {
		return smallArms ? getSlimTexture() : getTexture();
	}

	@Environment(EnvType.CLIENT)
	protected abstract Identifier getSlimTexture();
}
