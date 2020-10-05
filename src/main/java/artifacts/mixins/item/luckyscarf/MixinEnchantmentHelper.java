package artifacts.mixins.item.luckyscarf;

import artifacts.common.init.Items;
import artifacts.mixins.accessors.ItemStackAccessor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {

	@Inject(method = "getLevel", at = @At("RETURN"), cancellable = true)
	private static void increaseFortune(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> info) {
		//noinspection ConstantConditions
		LivingEntity holder = ((ItemStackAccessor)(Object) stack).getHolder() instanceof LivingEntity ? (LivingEntity) ((ItemStackAccessor)(Object) stack).getHolder() : null;
		if (enchantment == Enchantments.FORTUNE && CuriosApi.getCuriosHelper().findEquippedCurio(Items.LUCKY_SCARF, holder).isPresent()) {
			info.setReturnValue(info.getReturnValueI() + 1);
		}
	}
}
