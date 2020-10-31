package artifacts.common.item.trinket;

import artifacts.client.render.TrinketRenderHelper;
import artifacts.common.item.ArtifactItem;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.DispenserBlock;
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
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TrinketArtifactItem extends ArtifactItem implements Trinket {

	public TrinketArtifactItem(Settings settings) {
		super(settings);
		DispenserBlock.registerBehavior(this, TrinketItem.TRINKET_DISPENSER_BEHAVIOR);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		return Trinket.equipTrinket(player, hand);
	}

	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
	}

	/**
	 * Used to give a Curio a permanent status effect while wearing it.
	 * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
	 *
	 * @return The StatusEffectInstance to be applied
	 */
	public StatusEffectInstance getPermanentEffect() {
		return null;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		// TODO: check if same trinket is already equipped in another slot
		return true;
	}

	@Override
	public void onEquip(PlayerEntity player, ItemStack stack) {
		player.world.playSound(null, new BlockPos(player.getPos()), this.getEquipSound(), SoundCategory.NEUTRAL, 1, 1);
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
