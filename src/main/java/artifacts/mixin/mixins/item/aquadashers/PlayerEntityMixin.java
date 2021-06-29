package artifacts.mixin.mixins.item.aquadashers;

import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	@Inject(method = "tick", at = @At("HEAD"))
	private void updateWet(CallbackInfo info) {
		PlayerEntity self = (PlayerEntity) (Object) this;
		SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(self);

		if (self.isTouchingWater()) {
			swimAbilities.setWet(true);
		} else if (self.isOnGround()) {
			swimAbilities.setWet(false);
		}
	}
}
