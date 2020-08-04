package artifacts.mixins.item.flippers;

import artifacts.common.extensions.LivingEntityExtension;
import artifacts.common.init.Items;
import artifacts.common.item.curio.FlippersItem;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements LivingEntityExtension {

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

	@Override
	public double artifacts$getIncreasedSwimSpeed(double speed) {
		return CuriosApi.getCuriosHelper().findEquippedCurio(Items.FLIPPERS, (LivingEntity)(Object) this).isPresent()
				? speed * FlippersItem.SWIM_SPEED_MULTIPLIER : speed;
	}
}
