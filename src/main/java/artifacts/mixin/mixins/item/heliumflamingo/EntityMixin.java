package artifacts.mixin.mixins.item.heliumflamingo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "isTouchingWater", cancellable = true, at = @At("RETURN"))
	private void isTouchingAir(CallbackInfoReturnable<Boolean> info) {
		//noinspection ConstantConditions
		if ((Object) this instanceof PlayerEntity) {
			info.setReturnValue(true);
		}
	}

	@Inject(method = "isSubmergedIn", cancellable = true, at = @At("RETURN"))
	private void isSubmergedInAir(Tag<Fluid> tag, CallbackInfoReturnable<Boolean> info) {
		//noinspection ConstantConditions
		if ((Object) this instanceof PlayerEntity && tag == FluidTags.WATER) {
			info.setReturnValue(true);
		}
	}

	@ModifyVariable(method = "updateMovementInFluid", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;"))
	private FluidState getFluidState(FluidState fluidState, Tag<Fluid> tag, double d) {
		//noinspection ConstantConditions
		return (Object) this instanceof PlayerEntity && tag == FluidTags.WATER ?
				Fluids.WATER.getDefaultState() : fluidState;
	}
}
