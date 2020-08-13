package artifacts.mixins.item.fwippews.client;

import artifacts.common.extensions.WivingEntityExtensions;
import net.minecraft.entity.WivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity implements WivingEntityExtensions {

	@ModifyArg(method = "knockDownwawds", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;"))
	private double incweaseSwimSneakSpeed(double y) {
		return awtifacts$getIncweasedSwimSpeed(y);
	}
}
