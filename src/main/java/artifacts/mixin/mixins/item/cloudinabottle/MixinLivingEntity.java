package artifacts.mixin.mixins.item.cloudinabottle;

import artifacts.common.init.Items;
import artifacts.common.item.trinket.CloudInABottleItem;
import artifacts.common.trinkets.TrinketsHelper;
import artifacts.mixin.extensions.LivingEntityExtensions;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity implements LivingEntityExtensions {

	@Shadow
	protected boolean jumping;
	// Is entity double jumping in this tick
	@Unique
	private boolean artifacts$isDoubleJumping = false;
	// Has entity released jump key since last jump
	@Unique
	private boolean artifacts$jumpWasReleased = false;
	// Has entity double jumped during current airtime
	@Unique
	private boolean artifacts$hasDoubleJumped = false;

	public MixinLivingEntity(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow
	protected abstract void jump();

	@Shadow
	public abstract boolean isClimbing();

	@Unique
	@Override
	public void artifacts$doubleJump() {
		// Call the vanilla jump method
		// We modify the behaviour of this method in multiple places if artifacts$isDoubleJumping is true
		this.artifacts$isDoubleJumping = true;
		this.jump();

		// Play jump sound
		LivingEntity self = (LivingEntity) (Object) this;
		SoundEvent jumpSound = TrinketsHelper.isEquipped(Items.WHOOPEE_CUSHION, self) ?
				artifacts.common.init.SoundEvents.FART : SoundEvents.BLOCK_WOOL_FALL;
		this.playSound(jumpSound, 1, 0.9F + self.getRandom().nextFloat() * 0.2F);

		// Reset fall distance for fall damage
		this.fallDistance = 0;

		// Send double jump packet to server if we're on the client
		if (this.world.isClient) {
			ClientSidePacketRegistry.INSTANCE.sendToServer(CloudInABottleItem.C2S_DOUBLE_JUMPED_ID, new PacketByteBuf(Unpooled.buffer()));
		}

		this.artifacts$isDoubleJumping = false;
	}

	@ModifyVariable(method = "handleFallDamage", ordinal = 0, at = @At("HEAD"))
	private float reduceFallDistance(float fallDistance) {
		// FIXME: this probably also works if we didn't double jump, intended?
		if (TrinketsHelper.isEquipped(Items.CLOUD_IN_A_BOTTLE, (LivingEntity) (Object) this)) {
			fallDistance = Math.max(0, fallDistance - 3);
		}

		return fallDistance;
	}

	@SuppressWarnings("ConstantConditions")
	@Inject(method = "tickMovement", at = @At("HEAD"))
	private void invokeDoubleJump(CallbackInfo info) {
		LivingEntity self = (LivingEntity) (Object) this;
		artifacts$jumpWasReleased |= !this.jumping;

		if ((this.isOnGround() || this.isClimbing()) && !this.isTouchingWater()) {
			this.artifacts$hasDoubleJumped = false;
		}

		boolean flying = self instanceof PlayerEntity && ((PlayerEntity) self).abilities.flying;
		if (this.jumping && this.artifacts$jumpWasReleased && !this.isTouchingWater() && !this.isOnGround() && !this.hasVehicle()
				&& !this.artifacts$hasDoubleJumped && !flying && TrinketsHelper.isEquipped(Items.CLOUD_IN_A_BOTTLE, self)) {
			this.artifacts$doubleJump();
			this.artifacts$hasDoubleJumped = true;
		}
	}

	@Inject(method = "jump", at = @At("RETURN"))
	private void setJumpReleased(CallbackInfo info) {
		this.artifacts$jumpWasReleased = false;
	}

	// The next three injectors modify the behaviour of the vanilla jump
	// method if the artifacts$isDoubleJumping field is set to true

	@Inject(method = "getJumpVelocity", cancellable = true, at = @At("HEAD"))
	private void increaseBaseDoubleJumpVelocity(CallbackInfoReturnable<Float> info) {
		if (this.artifacts$isDoubleJumping) {
			info.setReturnValue(0.5f);
		}
	}

	@ModifyArg(method = "jump", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(DDD)V"))
	private double sprintingDoubleJumpUpwardVelocityMultiplier(double y) {
		return this.artifacts$isDoubleJumping && this.isSprinting() ? y * 1.5 : y;
	}

	@ModifyConstant(method = "jump", constant = @Constant(floatValue = 0.2f))
	private float sprintingDoubleJumpHorizontalVelocityMultiplier(float multiplier) {
		return this.artifacts$isDoubleJumping ? 0.65f : multiplier;
	}
}
