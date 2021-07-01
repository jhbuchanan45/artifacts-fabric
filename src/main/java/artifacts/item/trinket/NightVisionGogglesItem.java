package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.RenderLayer;
import artifacts.client.render.model.trinket.KittySlippersModel;
import artifacts.client.render.model.trinket.NightVisionGogglesModel;
import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class NightVisionGogglesItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/night_vision_goggles.png");
	private static final Identifier TEXTURE_GLOW = Artifacts.id("textures/entity/trinket/night_vision_goggles_glow.png");

	@Override
	public StatusEffectInstance getPermanentEffect() {
		return new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20, 0, true, false);
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(NightVisionGogglesModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new NightVisionGogglesModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		super.render(stack, slotReference, contextModel, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
		VertexConsumer buffer = ItemRenderer.getItemGlintConsumer(vertexConsumers, RenderLayer.unlit(TEXTURE_GLOW), false, false);
		getModel().render(matrices, buffer, 0xF000F0, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
	}

}
