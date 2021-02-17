package artifacts.mixin.mixins.item.heliumflamingo.client;

import artifacts.Artifacts;
import artifacts.item.trinket.HeliumFlamingoItem;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

	@Shadow public Input input;

	public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
		super(world, profile);
	}

	@Inject(method = "tickMovement", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;allowFlying:Z"))
	private void startAirSwimming(CallbackInfo info, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
		if (!this.abilities.allowFlying && !bl && this.input.jumping && !bl4 && HeliumFlamingoItem.canFly(this)) {
			if (this.abilityResyncCountdown == 0) {
				this.abilityResyncCountdown = 7;
			} else {
				// TODO: start air swimming
				Artifacts.LOGGER.info("Start air swimming");
			}
		}
	}
}
