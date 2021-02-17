package artifacts.mixin.mixins.event;

import artifacts.events.LivingEntityDamagedCallback;
import artifacts.events.LivingEntityHurtCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {LivingEntity.class, PlayerEntity.class})
public abstract class LivingEntitiesMixin extends Entity {

	public LivingEntitiesMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "applyDamage", at = @At("HEAD"))
	private void onEntityHurt(DamageSource source, float amount, CallbackInfo info) {
		if (!this.isInvulnerableTo(source)) {
			LivingEntityHurtCallback.EVENT.invoker().hurt((LivingEntity) (Object) this, source, amount);
		}
	}

	@Inject(method = "applyDamage", allow = 1, at = @At(value = "JUMP", opcode = Opcodes.IFNE))
	private void onEntityDamaged(DamageSource source, float amount, CallbackInfo info) {
		if (!this.isInvulnerableTo(source)) {
			LivingEntityDamagedCallback.EVENT.invoker().damage((LivingEntity) (Object) this, source, amount);
		}
	}
}
