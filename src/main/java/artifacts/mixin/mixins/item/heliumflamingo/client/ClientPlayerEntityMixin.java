package artifacts.mixin.mixins.item.heliumflamingo.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

	@Shadow public Input input;

	public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
		super(world, profile);
	}

	/*@Inject(method = "tickMovement", locals = LocalCapture.CAPTURE_FAILHARD,
			slice = @Slice(from = @At(value = "INVOKE:LAST", target = "Lnet/minecraft/client/network/ClientPlayerEntity;setSprinting(Z)V")),
			at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;allowFlying:Z"))
	private void startAirSwimming(CallbackInfo info, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
		if (!this.abilities.allowFlying && !bl && this.input.jumping && !bl4
				&& TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, this)) {
			if (this.abilityResyncCountdown == 0) {
				this.abilityResyncCountdown = 7;
			} else {
				Components.ARTIFACT_ABILITIES.get(this).toggleAirSwimming();
			}
		}
	}*/
}
