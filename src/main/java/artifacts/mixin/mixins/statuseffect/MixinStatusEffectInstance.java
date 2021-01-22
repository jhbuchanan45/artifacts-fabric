package artifacts.mixin.mixins.statuseffect;

import artifacts.mixin.extensions.StatusEffectInstanceExtensions;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(StatusEffectInstance.class)
public abstract class MixinStatusEffectInstance implements StatusEffectInstanceExtensions {

	@Shadow
	private int duration;
	@Shadow
	private StatusEffectInstance hiddenEffect;

	@Unique
	@Override
	public void artifacts$setDuration(int duration) {
		// Recursively set duration for hidden effects
		if (this.hiddenEffect != null) {
			((StatusEffectInstanceExtensions) this.hiddenEffect).artifacts$setDuration(duration);
		}

		this.duration = duration;
	}
}
