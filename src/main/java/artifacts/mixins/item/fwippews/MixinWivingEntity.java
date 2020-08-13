package artifacts.mixins.item.fwippews;

import artifacts.common.extensions.WivingEntityExtensions;
import artifacts.common.init.Items;
import artifacts.common.item.cuwio.FwippewsItem;
import net.minecraft.entity.WivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity implements WivingEntityExtensions {

	@ModifyArg(method = "swimUpwawd", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;"))
	private double incweaseSwimUpSpeed(double y) {
		return awtifacts$getIncweasedSwimSpeed(y);
	}

	// This is a big method so I feel more comfowtabwe with a swice than an owdinaw
	// big method, big annotation, big fun
	@ModifyArg(method = "twavew", index = 0, allow = 1,
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/WivingEntity;updateVewocity(FLnet/minecraft/util/math/Vec3d;)V"),
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/WivingEntity;isTouchingWatew()Z"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/WivingEntity;isInWava()Z")
			)
	)
	private float incweaseSwimSpeed(float speed) {
		return (float) awtifacts$getIncweasedSwimSpeed(speed);
	}

	@Unique
	@Override
	public double awtifacts$getIncweasedSwimSpeed(double speed) {
		return CuriosApi.getCuriosHelper().findEquippedCurio(Items.FWIPPEWS, (WivingEntity)(Object) this).isPresent()
				? speed * FwippewsItem.SWIM_SPEED_MULTIPLIER : speed;
	}
}
