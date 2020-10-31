package artifacts.mixins.item.superstitioushat;

import artifacts.common.init.Items;
import artifacts.common.util.TrinketsHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {

	@Inject(method = "getLooting", at = @At("RETURN"), cancellable = true)
	private static void increaseLooting(LivingEntity entity, CallbackInfoReturnable<Integer> info) {
		// Add 1 level of knockback with a minimum of 2
		if (TrinketsHelper.isEquipped(Items.POCKET_PISTON, entity)) {
			info.setReturnValue(info.getReturnValueI() + 1);
		}
	}
}
