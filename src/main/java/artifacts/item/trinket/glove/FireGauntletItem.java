package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.client.render.RenderLayer;
import artifacts.client.render.model.trinket.GloveModel;
import artifacts.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

public class FireGauntletItem extends GloveItem {

	private static final Identifier TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/fire_gauntlet_default.png");
	private static final Identifier TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/fire_gauntlet_slim.png");
	private static final Identifier TEXTURE_DEFAULT_GLOW = Artifacts.id("textures/entity/trinket/fire_gauntlet_default_glow.png");
	private static final Identifier TEXTURE_SLIM_GLOW = Artifacts.id("textures/entity/trinket/fire_gauntlet_slim_glow.png");

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		super.render(stack, slotReference, contextModel, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);

		boolean smallArms = hasSmallArms(entity);
		boolean hand = slotReference.inventory().getSlotType().getGroup().equals("hand");
		GloveModel model = this.getModel(smallArms);

		// The glow effect is achieved by rendering the glow texture unlit
		VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, RenderLayer.unlit(getGlowTexture(smallArms)), false, false);
		model.renderHand(hand, matrices, vertexConsumer, 0xF000F0, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void renderArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, Arm arm, boolean glint) {
		if (!player.isSpectator()) {
			super.renderArm(matrices, vertexConsumers, light, player, arm, glint);

			boolean smallArms = hasSmallArms(player);
			GloveModel model = getModel(smallArms);

			ModelPart armPart = arm == Arm.LEFT ? model.leftArm : model.rightArm;
			ModelPart sleevePart = arm == Arm.LEFT ? model.leftSleeve : model.rightSleeve;

			// Also render the glowy bit
			VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, RenderLayer.unlit(getGlowTexture(smallArms)), false, false);
			armPart.render(matrices, vertexConsumer, 0xF000F0, OverlayTexture.DEFAULT_UV);
			sleevePart.render(matrices, vertexConsumer, 0xF000F0, OverlayTexture.DEFAULT_UV);
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE_DEFAULT;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getSlimTexture() {
		return TEXTURE_SLIM;
	}

	@Environment(EnvType.CLIENT)
	protected Identifier getGlowTexture(boolean smallArms) {
		return smallArms ? TEXTURE_SLIM_GLOW : TEXTURE_DEFAULT_GLOW;
	}
}
