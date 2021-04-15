package artifacts.mixin.mixins.item.heliumflamingo.client;

import artifacts.Artifacts;
import artifacts.init.Components;
import artifacts.item.trinket.HeliumFlamingoItem;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.PacketByteBuf;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

	@Unique private boolean sprintKeyWasDown;
	@Unique private boolean isTogglingFlying;
	@Shadow @Final protected MinecraftClient client;

	public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
		super(world, profile);
	}

	@Inject(method = "tickMovement", at = @At("HEAD"))
	private void resetFieldLocals(CallbackInfo info) {
		this.isTogglingFlying = false;
	}

	@Inject(method = "tickMovement", slice = @Slice(to = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isFallFlying()Z")),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;sendAbilitiesUpdate()V"))
	private void setIsTogglingFlying(CallbackInfo ci) {
		this.sprintKeyWasDown = true;
	}

	@Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isFallFlying()Z"))
	private void startAirSwimming(CallbackInfo info) {
		// Like Input.tick() but for sprinting
		this.sprintKeyWasDown = this.client.options.keySprint.isPressed();

		if (this.client.options.keySprint.isPressed() && !this.isTogglingFlying && !this.sprintKeyWasDown
				&& !this.abilities.flying && !this.hasVehicle() && !this.isClimbing() && !this.onGround &&
				!this.isFallFlying() && !this.isTouchingWater() && !this.hasStatusEffect(StatusEffects.LEVITATION)) {
			Components.ARTIFACT_ABILITIES.get(this).setAbility(Artifacts.id("air_swimming"), true);

			PacketByteBuf startAirSwimmingPacket = PacketByteBufs.create();
			startAirSwimmingPacket.writeBoolean(true);
			ClientPlayNetworking.send(HeliumFlamingoItem.C2S_AIR_SWIMMING_ID, startAirSwimmingPacket);
		}
	}
}

