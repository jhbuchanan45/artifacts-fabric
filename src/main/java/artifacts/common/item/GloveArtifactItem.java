package artifacts.common.item;

import artifacts.client.render.TrinketRenderHelper;
import artifacts.client.render.model.curio.GloveModel;
import artifacts.common.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public abstract class GloveArtifactItem extends TrinketArtifactItem {

	protected Object modelDefault;
	protected Object modelSlim;

	public GloveArtifactItem(Settings settings) {
		super(settings);
	}

	@Environment(EnvType.CLIENT)
	protected static boolean hasSmallArms(Entity entity) {
		return entity instanceof AbstractClientPlayerEntity && ((AbstractClientPlayerEntity) entity).getModel().equals("slim");
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

	@Override
	@Environment(EnvType.CLIENT)
	public void render(String slot, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityModel<AbstractClientPlayerEntity> playerModel, AbstractClientPlayerEntity player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		boolean smallArms = hasSmallArms(player);
		boolean hand = slot.split(":")[0].equals(SlotGroups.HAND);

		GloveModel model = this.getModel(smallArms);
		model.setAngles(player, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		model.animateModel(player, limbAngle, limbDistance, tickDelta);
		TrinketRenderHelper.followBodyRotations(player, model);
		VertexConsumer vertexBuilder = ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(getTexture(smallArms)), false, false);
		model.renderHand(hand, matrices, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
	}
}
