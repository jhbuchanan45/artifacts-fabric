package artifacts.mixin.mixins.item.heliumflamingo.client;

import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

	@Shadow @Final protected MinecraftClient client;
	@Unique private boolean wasSprintKeyDown;
	@Unique private boolean wasSprintingOnGround;
	@Unique private boolean hasTouchedGround;

	@Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/Input;tick(Z)V", shift = At.Shift.AFTER))
	private void handleAirSwimmingInput(CallbackInfo info) {
		ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;
		boolean isSprintKeyDown = this.client.options.keySprint.isPressed();
		SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(self);

		if (!swimAbilities.isSwimming()) {
			if (self.isOnGround()) {
				hasTouchedGround = true;
			} else if (!swimAbilities.isSwimming()
					&& self.getAir() > 0
					&& TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, self)
					&& (self.isSwimming()
					|| isSprintKeyDown
					&& !wasSprintKeyDown
					&& !wasSprintingOnGround
					&& hasTouchedGround
					&& !self.isOnGround()
					&& (!self.isTouchingWater() || swimAbilities.isSinking())
					&& !self.isFallFlying()
					&& !self.getAbilities().flying
					&& !self.hasVehicle())) {
				swimAbilities.setSwimming(true);
				swimAbilities.syncSwimming();
				hasTouchedGround = false;
			}
		} else if (self.getAbilities().flying) {
			swimAbilities.setSwimming(false);
			swimAbilities.syncSwimming();
			hasTouchedGround = true;
		}

		wasSprintKeyDown = isSprintKeyDown;
		if (!isSprintKeyDown) {
			wasSprintingOnGround = false;
		} else if (self.isOnGround()) {
			wasSprintingOnGround = true;
		}
	}
}
