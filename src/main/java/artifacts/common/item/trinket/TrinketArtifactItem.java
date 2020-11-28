package artifacts.common.item.trinket;

import artifacts.client.render.TrinketRenderHelper;
import artifacts.client.render.model.trinket.AntidoteVesselModel;
import artifacts.common.init.Components;
import artifacts.common.item.ArtifactItem;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.MinecraftClient;
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
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public abstract class TrinketArtifactItem extends ArtifactItem implements Trinket {

	private Object model;

	public TrinketArtifactItem(Settings settings) {
		super(settings);
		DispenserBlock.registerBehavior(this, TrinketItem.TRINKET_DISPENSER_BEHAVIOR);
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getTrinketModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		if (effectsEnabled(stack)) {
			return this.applyModifiers(group, slot, uuid, stack);
		}
		return HashMultimap.create();
	}

	public Multimap<EntityAttribute, EntityAttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		return HashMultimap.create();
	}

	@Override
	public void tick(PlayerEntity player, ItemStack stack) {
		if (effectsEnabled(stack)) {
			effectTick(player, stack);
		}
	}

	public void effectTick(PlayerEntity player, ItemStack stack) { }

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flags) {
		super.appendTooltip(stack, world, tooltip, flags);
		tooltip.add(new TranslatableText(getEffectsEnabledLanguageKey(stack)).formatted(Formatting.GOLD));
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		// Toggle artifact effects when sneak right-clicking
		if (user.isSneaking()) {
			ItemStack stack = user.getStackInHand(hand);
			Components.TRINKET_ENABLED.get(stack).invert();

			// Show enabled/disabled message above hotbar
			Formatting enabledColor = effectsEnabled(stack) ? Formatting.GREEN : Formatting.RED;
			Text enabledText = new TranslatableText(getEffectsEnabledLanguageKey(stack)).formatted(enabledColor);
			MinecraftClient.getInstance().inGameHud.setOverlayMessage(enabledText, false);

			return TypedActionResult.success(stack);
		}

		TypedActionResult<ItemStack> actionResult = Trinket.equipTrinket(user, hand);

		// Play right click equip sound
		if (actionResult.getResult().isAccepted()) {
			user.playSound(this.getEquipSound(), 1.0f, 1.0f);
		}

		return actionResult;
	}

	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
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
	protected BipedEntityModel<LivingEntity> getModel() {
		if (model == null) {
			model = createModel();
		}

		//noinspection unchecked
		return (BipedEntityModel<LivingEntity>) model;
	}

	@Environment(EnvType.CLIENT)
	protected abstract BipedEntityModel<LivingEntity> createModel();

	public static boolean effectsEnabled(ItemStack stack) {
		return Components.TRINKET_ENABLED.get(stack).get();
	}

	public static String getEffectsEnabledLanguageKey(ItemStack stack) {
		return effectsEnabled(stack) ? "artifacts.trinket.effectsenabled" : "artifacts.trinket.effectsdisabled";
	}
}
