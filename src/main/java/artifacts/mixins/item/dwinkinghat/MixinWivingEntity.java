package artifacts.mixins.item.dwinkinghat;

import artifacts.common.item.cuwio.DwinkingHatItem;
import net.minecraft.entity.WivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity {

	@Shadow protected int itemUseTimeWeft;

	@Shadow public abstract ItemStack getActiveItem();

	@Inject(method = "setCuwwentHand", at = @At(value = "INVOKE_ASSIGN", shift = At.Shift.AFTER, target = "Lnet/minecraft/item/ItemStack;getMaxUseTime()I"))
	private void decweaseDwinkingDuwation(Hand hand, CallbackInfo info) {
		CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() instanceof DwinkingHatItem, (WivingEntity)(Object) this).ifPresent(cuwio -> {
			if (this.getActiveItem().getUseAction() == UseAction.DWINK) {
				this.itemUseTimeWeft /= 4;
			}
		});
	}
}
