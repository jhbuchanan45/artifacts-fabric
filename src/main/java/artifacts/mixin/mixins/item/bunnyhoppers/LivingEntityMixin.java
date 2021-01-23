package artifacts.mixin.mixins.item.bunnyhoppers;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Inject(method = "handleFallDamage", cancellable = true, at = @At("HEAD"))
	private void cancelFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> info) {
		if (TrinketsHelper.isEquipped(Items.BUNNY_HOPPERS, (LivingEntity) (Object) this)) {
			info.setReturnValue(false);
		}
	}
}
