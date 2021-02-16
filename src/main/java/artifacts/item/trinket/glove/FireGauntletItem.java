package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.client.render.RenderTypes;
import artifacts.client.render.TrinketRenderHelper;
import artifacts.client.render.model.trinket.GloveModel;
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
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class FireGauntletItem extends GloveItem {

	private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MODID, "textures/entity/trinket/fire_gauntlet_default.png");
	private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MODID, "textures/entity/trinket/fire_gauntlet_slim.png");
	private static final Identifier TEXTURE_DEFAULT_GLOW = new Identifier(Artifacts.MODID, "textures/entity/trinket/fire_gauntlet_default_glow.png");
	private static final Identifier TEXTURE_SLIM_GLOW = new Identifier(Artifacts.MODID, "textures/entity/trinket/fire_gauntlet_slim_glow.png");

	@Override
	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
	}

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

		// The glow effect is achieved by rendering the glow texture unlit
		vertexBuilder = ItemRenderer.getItemGlintConsumer(vertexConsumers, RenderTypes.unlit(getGlowTexture(smallArms)), false, false);
		model.renderHand(hand, matrices, vertexBuilder, 0xF000F0, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
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
