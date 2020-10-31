package artifacts.mixins.item.bunnyhoppers;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

	@Inject(method = "handleFallDamage", cancellable = true, at = @At("HEAD"))
	private void cancelFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> info) {
		// TODO: Port to Trinkets
		/*CuriosApi.getCuriosHelper().findEquippedCurio(Items.BUNNY_HOPPERS, (LivingEntity)(Object) this).ifPresent(curio -> {
			info.setReturnValue(false);
		});*/
	}
}
