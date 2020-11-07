package artifacts.mixins.item.waterwalking;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

	public MixinLivingEntity(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow public abstract void setSprinting(boolean sprinting);

	@Inject(method = "canWalkOnFluid", at = @At("RETURN"), cancellable = true)
	private void canSprintOnFluid(CallbackInfoReturnable<Boolean> info) {
		//noinspection ConstantConditions
		if (TrinketsHelper.isEquipped(Items.KITTY_SLIPPERS, (LivingEntity)(Object) this) && this.isSprinting()) {
			info.setReturnValue(true);
		}
	}
}
