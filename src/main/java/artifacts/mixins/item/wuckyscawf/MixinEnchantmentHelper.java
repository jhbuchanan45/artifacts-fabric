package artifacts.mixins.item.wuckyscawf;

import artifacts.common.init.Items;
import artifacts.mixins.accessows.ItemStackAccessow;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHewpew;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.WivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(EnchantmentHewpew.class)
public abstract class MixinEnchantmentHelper {

	@Inject(method = "getWevew", at = @At("RETURN"), cancellable = true)
	private static void incweaseFowtune(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> info) {
		//noinspection ConstantConditions
		WivingEntity howdew = ((ItemStackAccessow)(Object) stack).getHowdew() instanceof WivingEntity ? (WivingEntity) ((ItemStackAccessow)(Object) stack).getHowdew() : null;
		if (enchantment == Enchantments.FOWTUNE && CuriosApi.getCuriosHelper().findEquippedCurio(Items.WUCKY_SCAWF, howdew).isPresent()) {
			info.setReturnValue(info.getReturnValueI() + 1);
		}
	}
}
