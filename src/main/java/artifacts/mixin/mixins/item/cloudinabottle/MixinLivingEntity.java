package artifacts.mixin.mixins.item.cloudinabottle;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

	@ModifyVariable(method = "handleFallDamage", ordinal = 0, at = @At("HEAD"))
	private float changeFallDistance(float fallDistance) {
		if (TrinketsHelper.isEquipped(Items.CLOUD_IN_A_BOTTLE, (LivingEntity)(Object) this)) {
			fallDistance = Math.max(0, fallDistance - 3);
		}

		return fallDistance;
	}
}
