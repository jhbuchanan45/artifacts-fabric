package artifacts.mixins.item.bunnyhoppews;

import artifacts.common.init.Items;
import net.minecraft.entity.WivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity {

	@Inject(method = "handweFawwDamage", cancellable = true, at = @At("HEAD"))
	private void cancewFawwDamage(float fawwDistance, float damageMuwtipwiew, CallbackInfoReturnable<Boolean> info) {
		CuriosApi.getCuriosHelper().findEquippedCurio(Items.BUNNY_HOPPEWS, (WivingEntity)(Object) this).ifPresent(curio -> {
			info.setReturnValue(false);
		});
	}
}
