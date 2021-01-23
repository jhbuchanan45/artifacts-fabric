package artifacts.mixin.mixins.item.flippers;

import artifacts.common.init.Items;
import artifacts.common.item.trinket.FlippersItem;
import artifacts.common.trinkets.TrinketsHelper;
import artifacts.mixin.extensions.LivingEntityExtensions;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityExtensions {

	@ModifyArg(method = "swimUpward", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;"))
	private double increaseSwimUpSpeed(double y) {
		return artifacts$getIncreasedSwimSpeed(y);
	}

	// This is a big method so I feel more comfortable with a slice than an ordinal
	// big method, big annotation, big fun
	@ModifyArg(method = "travel", index = 0, allow = 1,
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"),
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isTouchingWater()Z"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInLava()Z")
			)
	)
	private float increaseSwimSpeed(float speed) {
		return (float) artifacts$getIncreasedSwimSpeed(speed);
	}

	@Unique
	@Override
	public double artifacts$getIncreasedSwimSpeed(double speed) {
		return TrinketsHelper.isEquipped(Items.FLIPPERS, (LivingEntity) (Object) this)
				? speed * FlippersItem.SWIM_SPEED_MULTIPLIER : speed;
	}
}
