package artifacts.mixin.mixins.compat.origins;

import artifacts.item.UmbrellaItem;
import io.github.apace100.origins.Origins;
import io.github.apace100.origins.power.factory.condition.ConditionFactory;
import io.github.apace100.origins.util.SerializableData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@Mixin(value = ConditionFactory.class, remap = false)
public class ConditionFactoryMixin<T> {

	@Shadow
	@Mutable
	@Final
	private BiFunction<SerializableData.Instance, T, Boolean> condition;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void umbrellaBlocksSun(Identifier identifier, SerializableData data, BiFunction<SerializableData.Instance, T, Boolean> condition, CallbackInfo info) {
		if (identifier.equals(new Identifier(Origins.MODID, "exposed_to_sun"))) {

			// Wrapper around original condition
			this.condition = (instance, t) -> {
				boolean exposedToSun = condition.apply(instance, t);

				if (exposedToSun && t instanceof LivingEntity) {
					LivingEntity entity = (LivingEntity) t;
					return !UmbrellaItem.isHeldUpInEitherHand(entity);
				}

				return exposedToSun;
			};
		}
	}
}
