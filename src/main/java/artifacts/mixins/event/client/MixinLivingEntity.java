package artifacts.mixins.event.client;

import artifacts.common.events.PlayHurtSoundCallback;
import artifacts.common.extensions.LivingEntityExtension;
import artifacts.common.init.Items;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements LivingEntityExtension {

	@Shadow protected abstract float getSoundVolume();
	@Shadow protected abstract float getSoundPitch();

	@Inject(method = "handleStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getHurtSound(Lnet/minecraft/entity/damage/DamageSource;)Lnet/minecraft/sound/SoundEvent;"))
	private void onClientPlayHurtSound(byte status, CallbackInfo info) {
		PlayHurtSoundCallback.EVENT.invoker().play((LivingEntity)(Object) this, this.getSoundVolume(), this.getSoundPitch());
	}

	/**
	 * Show the effect as permanent, which normally only happens if the duration is >= 32767
	 * Doing it here makes sure it is set to permanent everytime the server sent an update packet
	 */
	// TODO: make this an event
	@Inject(method = "applyStatusEffect", at = @At("HEAD"))
	private void setStatusEffectPermanent(StatusEffectInstance effect, CallbackInfo info) {
		if (effect.getEffectType() == StatusEffects.JUMP_BOOST && CuriosApi.getCuriosHelper().findEquippedCurio(Items.BUNNY_HOPPERS, (LivingEntity) (Object) this).isPresent()) {
			effect.setPermanent(true);
		}
	}
}
