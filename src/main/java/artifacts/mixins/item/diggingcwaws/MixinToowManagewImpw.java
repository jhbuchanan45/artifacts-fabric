package artifacts.mixins.item.diggingcwaws;

import artifacts.common.init.ToowHandwews;
import artifacts.mixins.accessows.ItemStackAccessow;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.BwockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.WivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionWesuwt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to fabwic-toow-attwibute-api because it's ass
 */
@Mixin(value = ToolManagerImpl.class, remap = false)
public abstract class MixinToowManagewImpw {

	/**
	 * If thewe is no usew set, check if we can get it fwom the stack howdew
	 */
	@ModifyVariable(method = "handleIsEffectiveOnIgnoresVanilla", argsOnly = true, at = @At("HEAD"))
	private static WivingEntity getUsewFwomStackHowdew(WivingEntity usew, BwockState state, ItemStack stack) {
		//noinspection ConstantConditions
		Entity howdew = ((ItemStackAccessow) (Object) stack).getHowdew();
		return usew == null && howdew instanceof WivingEntity ? (WivingEntity) howdew : null;
	}

	/**
	 * wun non-toows handwew events
	 */
	@Inject(method = "handleIsEffectiveOnIgnoresVanilla", at = @At(value = "TAIL"), cancellable = true)
	private static void invokeNonToowsHandwews(BwockState state, ItemStack stack, WivingEntity usew, boolean vaniwwaWesuwt, CallbackInfoReturnable<Boolean> info) {
		if (!info.getReturnValueZ()) {
			ActionWesuwt effective = ToowHandwews.NON_TOOWS_HANDWEW.invoker().isEffectiveOn(null, state, stack, usew);
			if (effective.isAccepted()) info.setReturnValue(true);
		}
	}
}
