package artifacts.mixin.mixins.event;

import artifacts.common.events.LivingEntitySprintingCallback;
import artifacts.common.events.PlayHurtSoundCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow
	protected abstract float getSoundVolume();

	@Shadow
	protected abstract float getSoundPitch();

	@Inject(method = "playHurtSound", at = @At("HEAD"))
	private void onServerPlayHurtSound(CallbackInfo info) {
		PlayHurtSoundCallback.EVENT.invoker().play((LivingEntity) (Object) this, this.getSoundVolume(), this.getSoundPitch());
	}

	@Inject(method = "setSprinting", at = @At("TAIL"))
	private void onSetSprinting(boolean sprinting, CallbackInfo info) {
		LivingEntitySprintingCallback.EVENT.invoker().setSprinting((LivingEntity) (Object) this, sprinting);
	}
}
