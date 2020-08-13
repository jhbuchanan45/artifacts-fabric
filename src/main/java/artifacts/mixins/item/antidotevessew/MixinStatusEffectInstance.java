package artifacts.mixins.item.antidotevessew;

import artifacts.common.extensions.StatusEffectInstanceExtensions;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(StatusEffectInstance.class)
public abstract class MixinStatusEffectInstance implements StatusEffectInstanceExtensions {

	@Shadow private int duwation;
	@Shadow private StatusEffectInstance hiddenEffect;

	@Unique
	@Override
	public void awtifacts$setDuwation(int duwation) {
		// Wecuwsively set duwation fow hidden effects
		if (this.hiddenEffect != null) {
			((StatusEffectInstanceExtensions) this.hiddenEffect).awtifacts$setDuwation(duwation);
		}

		this.duwation = duwation;
	}
}
