package artifacts.item.trinket.glove;

import artifacts.client.render.TrinketRenderHelper;
import artifacts.client.render.model.trinket.GloveModel;
import artifacts.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

public abstract class GloveItem extends TrinketArtifactItem {

	protected Object modelSlim;

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return (group.equals(SlotGroups.HAND) || group.equals(SlotGroups.OFFHAND)) && slot.equals(Slots.GLOVES);
	}

	@Environment(EnvType.CLIENT)
	protected static boolean hasSmallArms(Entity entity) {
		return entity instanceof AbstractClientPlayerEntity && ((AbstractClientPlayerEntity) entity).getModel().equals("slim");
	}

	@Environment(EnvType.CLIENT)
	protected GloveModel getModel(boolean smallArms) {
		return smallArms ? getSlimModel() : (GloveModel) getModel();
	}

	@Environment(EnvType.CLIENT)
	protected final GloveModel getSlimModel() {
		if (modelSlim == null) {
			modelSlim = createModel(true);
		}
		return (GloveModel) modelSlim;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected final GloveModel createModel() {
		return createModel(false);
	}

	@Environment(EnvType.CLIENT)
	protected GloveModel createModel(boolean smallArms) {
		return new GloveModel(smallArms);
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

	@Environment(EnvType.CLIENT)
	public void renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, Arm arm, boolean glint) {
		if (!player.isSpectator()) {
			boolean smallArms = hasSmallArms(player);
			GloveModel model = getModel(smallArms);

			ModelPart armPart = arm == Arm.LEFT ? model.leftArm : model.rightArm;
			ModelPart sleevePart = arm == Arm.LEFT ? model.leftSleeve : model.rightSleeve;

			model.setVisible(false);
			armPart.visible = sleevePart.visible = true;

			model.sneaking = false;
			model.handSwingProgress = model.leaningPitch = 0;
			model.setAngles(player, 0, 0, 0, 0, 0);
			armPart.pitch = sleevePart.pitch = 0;

			armPart.render(matrices, ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(getTexture(smallArms)), false, glint), light, OverlayTexture.DEFAULT_UV);
			sleevePart.render(matrices, ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(getTexture(smallArms)), false, glint), light, OverlayTexture.DEFAULT_UV);
		}
	}
}
