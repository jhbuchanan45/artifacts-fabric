package artifacts.mixin.mixins.item.waterwalking;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow public abstract void setSprinting(boolean sprinting);

	@Inject(method = "canWalkOnFluid", at = @At("RETURN"), cancellable = true)
	private void canSprintOnFluid(Fluid fluid, CallbackInfoReturnable<Boolean> info) {
		//noinspection ConstantConditions
		if (TrinketsHelper.isEquipped(Items.KITTY_SLIPPERS, (LivingEntity)(Object) this) && fluid.isIn(FluidTags.WATER)) {
			info.setReturnValue(true);
		}
	}
}
