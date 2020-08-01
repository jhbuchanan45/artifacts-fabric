package artifacts.mixins.item.antidotevessel;

import artifacts.common.extensions.StatusEffectInstanceExtension;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(StatusEffectInstance.class)
public abstract class MixinStatusEffectInstance implements StatusEffectInstanceExtension {

    @Shadow private int duration;
    @Shadow private StatusEffectInstance hiddenEffect;

	@Unique
	@Override
	public void artifacts$setDuration(int duration) {
		// Recursively set duration for hidden effects
		if (this.hiddenEffect != null) {
			((StatusEffectInstanceExtension) this.hiddenEffect).artifacts$setDuration(duration);
		}

		this.duration = duration;
	}
}
