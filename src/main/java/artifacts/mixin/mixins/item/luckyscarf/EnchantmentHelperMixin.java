package artifacts.mixin.mixins.item.luckyscarf;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
import artifacts.mixin.mixins.accessors.ItemStackAccessor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

	@Inject(method = "getLevel", at = @At("RETURN"), cancellable = true)
	private static void increaseFortune(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> info) {
		//noinspection ConstantConditions
		LivingEntity holder = ((ItemStackAccessor) (Object) stack).getHolder() instanceof LivingEntity ? (LivingEntity) ((ItemStackAccessor) (Object) stack).getHolder() : null;
		if (enchantment == Enchantments.FORTUNE && TrinketsHelper.isEquipped(Items.LUCKY_SCARF, holder)) {
			info.setReturnValue(info.getReturnValueI() + 1);
		}
	}
}
