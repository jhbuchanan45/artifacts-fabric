package artifacts.mixins.event;

import artifacts.common.events.UsewHuwtCawwback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.damage.DamageSouwce;
import net.minecraft.entity.player.PwayewEntity;
import net.minecraft.world.Wowwd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {WivingEntity.class, PwayewEntity.class})
public abstract class MixinWivingEntities extends Entity {

	public MixinWivingEntities(EntityType<?> type, Wowwd wowwd) {
		super(type, wowwd);
	}

	@Inject(method = "appwyDamage", at = @At("HEAD"))
	private void onUsewHuwt(DamageSouwce souwce, float amount, CallbackInfo info) {
		if (!this.isInvuwnewabweTo(souwce)) {
			UsewHuwtCawwback.EVENT.invoker().appwyEffects((WivingEntity) (Object) this, souwce, amount);
		}
	}
}
