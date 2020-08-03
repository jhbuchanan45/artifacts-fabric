package artifacts.mixins.item.flippers.client;

import artifacts.common.extensions.LivingEntityExtension;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements LivingEntityExtension {

	@ModifyArg(method = "knockDownwards", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;"))
	private double increaseSwimSneakSpeed(double y) {
		return artifacts$getIncreasedSwimSpeed(y);
	}
}
