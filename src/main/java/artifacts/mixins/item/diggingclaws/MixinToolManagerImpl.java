package artifacts.mixins.item.diggingclaws;

import artifacts.common.init.ToolHandlers;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
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
    private static LivingEntity getUserFromStackHolder(LivingEntity user, BlockState state, ItemStack stack) {
        //noinspection ConstantConditions
        Entity holder = ((ItemStackAccessor)(Object) stack).getHolder();
        return user == null && holder instanceof LivingEntity ? (LivingEntity) holder : null;
    }

    /**
     * Run our nontool toolhandler for all items
     */
    @Inject(method = "handleIsEffectiveOnIgnoresVanilla", at = @At(value = "TAIL"), cancellable = true)
    private static void invokeNonToolsHandlers(BlockState state, ItemStack stack, LivingEntity user, boolean vanillaResult, CallbackInfoReturnable<Boolean> info) {
        if (!info.getReturnValueZ()) {
            ActionResult effective = ToolHandlers.NON_TOOLS_HANDLER.invoker().isEffectiveOn(null, state, stack, user);
            if (effective.isAccepted()) info.setReturnValue(true);
        }
    }
}
