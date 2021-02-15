package artifacts.mixin.mixins.item.heliumflamingo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Unique
	private boolean isSubmergedInWater;

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

	@Redirect(method = "updateMovementInFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;getHeight(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F"))
	private float getHeight(FluidState fluidState, BlockView world, BlockPos pos, Tag<Fluid> tag, double d) {
		//noinspection ConstantConditions
		if ((Object) this instanceof PlayerEntity && tag == FluidTags.WATER) {
			return 1;
		}

		return fluidState.getHeight(world, pos);
	}

	@Redirect(method = "updateMovementInFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;getVelocity(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/Vec3d;"))
	private Vec3d getVelocity(FluidState fluidState, BlockView world, BlockPos pos, Tag<Fluid> tag, double d) {
		//noinspection ConstantConditions
		if ((Object) this instanceof PlayerEntity && tag == FluidTags.WATER) {
			return Vec3d.ZERO;
		}

		return fluidState.getVelocity(world, pos);
	}

	@Redirect(method = "updateMovementInFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/tag/Tag;)Z"))
	private boolean isIn(FluidState fluidState, Tag<Fluid> tag) {
		//noinspection ConstantConditions
		if ((Object) this instanceof PlayerEntity && tag == FluidTags.WATER) {
			return true;
		}

		return fluidState.isIn(tag);
	}
}
