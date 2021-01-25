package artifacts.mixin.mixins.event.client;

import artifacts.events.PlayHurtSoundCallback;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Shadow
	protected abstract float getSoundVolume();

	@Shadow
	protected abstract float getSoundPitch();

	@Inject(method = "handleStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getHurtSound(Lnet/minecraft/entity/damage/DamageSource;)Lnet/minecraft/sound/SoundEvent;"))
	private void onClientPlayHurtSound(byte status, CallbackInfo info) {
		PlayHurtSoundCallback.EVENT.invoker().play((LivingEntity) (Object) this, this.getSoundVolume(), this.getSoundPitch());
	}
}
