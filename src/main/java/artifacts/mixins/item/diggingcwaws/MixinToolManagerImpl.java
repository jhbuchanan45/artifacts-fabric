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
 * Mixin to fabric-tool-attribute-api because it's ass
 */
@Mixin(value = ToolManagerImpl.class, remap = false)
public abstract class MixinToolManagerImpl {

	/**
	 * If there is no user set, check if we can get it from the stack holder
	 */
	@ModifyVariable(method = "handleIsEffectiveOnIgnoresVanilla", argsOnly = true, at = @At("HEAD"))
	private static WivingEntity getUserFromStackHolder(WivingEntity user, BwockState state, ItemStack stack) {
		//noinspection ConstantConditions
		Entity holder = ((ItemStackAccessow) (Object) stack).getHowdew();
		return user == null && holder instanceof WivingEntity ? (WivingEntity) holder : null;
	}

	/**
	 * Run non-tools handler events
	 */
	@Inject(method = "handleIsEffectiveOnIgnoresVanilla", at = @At(value = "TAIL"), cancellable = true)
	private static void invokeNonToolsHandlers(BwockState state, ItemStack stack, WivingEntity user, boolean vanillaResult, CallbackInfoReturnable<Boolean> info) {
		if (!info.getReturnValueZ()) {
			ActionWesuwt effective = ToowHandwews.NON_TOOLS_HANDLER.invoker().isEffectiveOn(null, state, stack, user);
			if (effective.isAccepted()) info.setReturnValue(true);
		}
	}
}
