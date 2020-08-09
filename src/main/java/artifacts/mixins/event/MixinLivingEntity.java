package artifacts.mixins.event;

import artifacts.common.events.PlayHurtSoundCallback;
import artifacts.common.events.UserHurtCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

	public MixinLivingEntity(EntityType<?> type, World world) {
		super(type, world);
	}

    @Shadow protected abstract float getSoundVolume();

    @Shadow protected abstract float getSoundPitch();

	@Inject(method = "playHurtSound", at = @At("HEAD"))
	private void onServerPlayHurtSound(DamageSource source, CallbackInfo info) {
		PlayHurtSoundCallback.EVENT.invoker().play((LivingEntity)(Object) this, this.getSoundVolume(), this.getSoundPitch());
	}

	@Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"))
	private void onUserHurt(DamageSource source, float amount, CallbackInfo info) {
		UserHurtCallback.EVENT.invoker().applyEffects((LivingEntity)(Object) this, source, amount);
	}
}
