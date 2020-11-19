package artifacts.common.item.trinket;

import artifacts.client.render.TrinketRenderHelper;
import artifacts.common.init.Components;
import artifacts.common.item.ArtifactItem;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public abstract class TrinketArtifactItem extends ArtifactItem implements Trinket {

	public TrinketArtifactItem(Settings settings) {
		super(settings);
		DispenserBlock.registerBehavior(this, TrinketItem.TRINKET_DISPENSER_BEHAVIOR);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flags) {
		super.appendTooltip(stack, world, tooltip, flags);
		String key = Components.TRINKET_ENABLED.get(stack).get() ? "artifacts.trinket.effectsenabled" : "artifacts.trinket.effectsdisabled";
		tooltip.add(new TranslatableText(key).formatted(Formatting.GOLD));
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		// Toggle artifact effects when sneak right-clicking
		if (player.isSneaking()) {
			ItemStack stack = player.getStackInHand(hand);
			Components.TRINKET_ENABLED.get(stack).invert();
			return TypedActionResult.success(stack);
		}

		TypedActionResult<ItemStack> actionResult = Trinket.equipTrinket(player, hand);

		// Play right click equip sound
		if (actionResult.getResult().isAccepted()) {
			player.playSound(this.getEquipSound(), 1.0f, 1.0f);
		}

		return actionResult;
	}

	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
	}

	/**
	 * Used to give a Trinket a permanent status effect while wearing it.
	 * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
	 *
	 * @return The StatusEffectInstance to be applied
	 */
	public StatusEffectInstance getPermanentEffect() {
		return null;
	}

	@Environment(EnvType.CLIENT)
	public void render(String slot, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityModel<AbstractClientPlayerEntity> playerModel,
	                   AbstractClientPlayerEntity player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		BipedEntityModel<LivingEntity> model = this.getModel();
		model.setAngles(player, limbAngle, limbDistance, animationProgress, animationProgress, headPitch);
		model.animateModel(player, limbAngle, limbDistance, tickDelta);
		TrinketRenderHelper.followBodyRotations(player, model);
		VertexConsumer vertexBuilder = ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(this.getTexture()), false, false);
		model.render(matrices, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
	}

	@Environment(EnvType.CLIENT)
	protected abstract Identifier getTexture();

	@Environment(EnvType.CLIENT)
	protected abstract BipedEntityModel<LivingEntity> getModel();
}
