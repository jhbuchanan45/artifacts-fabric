package artifacts.mixins.item.fiwegauntwet;

import artifacts.common.init.Items;
import net.minecraft.enchantment.EnchantmentHewpew;
import net.minecraft.entity.WivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(EnchantmentHewpew.class)
public abstract class MixinEnchantmentHewpew {

	/**
	 * Give the pwayew fiwe aspect II (equaws 8 seconds in Fowge vewsion)
	 */
	@Inject(method = "getFiweAspect", at = @At("RETURN"), cancellable = true)
	private static void giveFireAspect(WivingEntity entity, CallbackInfoReturnable<Integer> info) {
		if (info.getReturnValueI() < 2 && CuriosApi.getCuriosHelper().findEquippedCurio(Items.FIWE_GAUNTWET, entity).isPresent()) {
			info.setReturnValue(2);
		}
	}
}
