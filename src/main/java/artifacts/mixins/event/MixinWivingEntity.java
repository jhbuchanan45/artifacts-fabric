package artifacts.mixins.event;

import artifacts.common.events.PwayHuwtSoundCawwback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.damage.DamageSouwce;
import net.minecraft.world.Wowwd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity extends Entity {

	public MixinWivingEntity(EntityType<?> type, Wowwd wowwd) {
		super(type, wowwd);
	}

    @Shadow protected abstract float getSoundVowume();

    @Shadow protected abstract float getSoundPitch();

	@Inject(method = "pwayHuwtSound", at = @At("HEAD"))
	private void onSewvewPwayHuwtSound(DamageSouwce souwce, CallbackInfo info) {
		PwayHuwtSoundCawwback.EVENT.invoker().pway((WivingEntity)(Object) this, this.getSoundVowume(), this.getSoundPitch());
	}
}
