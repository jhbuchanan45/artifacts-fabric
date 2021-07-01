package artifacts.mixin.mixins.item.heliumflamingo;

import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.init.Items;
import artifacts.init.SoundEvents;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	
	@Inject(method = "tick", at = @At("TAIL"))
	private void stopAirSwimming(CallbackInfo info) {
		PlayerEntity self = (PlayerEntity) (Object) this;
		SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(self);

		if (swimAbilities.isSwimming()) {
			if (!TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, self) || self.getAir() <= 0
					|| self.isTouchingWater() && !self.isSwimming() && !swimAbilities.isSinking()
					|| (!self.isTouchingWater() || swimAbilities.isSinking()) && self.isOnGround()) {
				swimAbilities.setSwimming(false);
				if (!self.isOnGround() && !self.isTouchingWater()) {
					self.playSound(SoundEvents.POP, 0.5F, 0.75F);
				}
			}

			if (TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, self) && !self.isSubmergedIn(FluidTags.WATER)) {
				// TODO: durability
				/*if (self.age % 20 == 0) {
					damageEquippedStacks(self);
				}*/

				// TODO: config
				if (!self.getAbilities().invulnerable /*&& ModConfig.server.heliumFlamingo.airSupplyDrainRate.get() > 0*/) {
					// compensate for bonus air
					int airSupply = self.getAir() - 4;
					self.setAir(airSupply - 2 /*ModConfig.server.heliumFlamingo.airSupplyDrainRate.get()*/);
				}
			}
		}
	}
}
