package artifacts.mixin.mixins.item.kittyslippers;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RevengeGoal.class)
public abstract class MixinRevengeGoal extends TrackTargetGoal {

	public MixinRevengeGoal(MobEntity mob, boolean checkVisibility) {
		super(mob, checkVisibility);
	}

	@Inject(method = "canStart", at = @At("HEAD"), cancellable = true)
	private void cancelRevenge(CallbackInfoReturnable<Boolean> info) {
		LivingEntity attacker = this.mob.getAttacker();
		if (this.mob.getType() == EntityType.CREEPER && TrinketsHelper.isEquipped(Items.KITTY_SLIPPERS, attacker)) {
			info.setReturnValue(false);
		}
	}
}
