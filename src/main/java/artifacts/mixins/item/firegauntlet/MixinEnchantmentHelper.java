package artifacts.mixins.item.firegauntlet;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {

	/**
	 * Give the player fire aspect II (equals 8 seconds in Forge version)
	 */
	@Inject(method = "getFireAspect", at = @At("RETURN"), cancellable = true)
	private static void giveFireAspect(LivingEntity entity, CallbackInfoReturnable<Integer> info) {
		if (info.getReturnValueI() < 2 && TrinketsHelper.isEquipped(Items.FIRE_GAUNTLET, entity)) {
			info.setReturnValue(2);
		}
	}
}
