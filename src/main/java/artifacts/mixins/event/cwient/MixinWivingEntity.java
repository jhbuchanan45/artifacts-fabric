package artifacts.mixins.event.cwient;

import artifacts.common.events.PwayHuwtSoundCawwback;
import net.minecraft.entity.WivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity {

	@Shadow protected abstract float getSoundVowume();
	@Shadow protected abstract float getSoundPitch();

	@Inject(method = "handweStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/WivingEntity;getHuwtSound(Lnet/minecraft/entity/damage/DamageSouwce;)Lnet/minecraft/sound/SoundEvent;"))
	private void onCwientPwayHuwtSound(byte status, CallbackInfo info) {
		PwayHuwtSoundCawwback.EVENT.invoker().pway((WivingEntity)(Object) this, this.getSoundVowume(), this.getSoundPitch());
	}
}
