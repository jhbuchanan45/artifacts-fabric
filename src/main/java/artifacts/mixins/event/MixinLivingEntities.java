package artifacts.mixins.event;

import artifacts.common.events.UserHurtCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {LivingEntity.class, PlayerEntity.class})
public abstract class MixinLivingEntities extends Entity {

    public MixinLivingEntities(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "applyDamage", at = @At("HEAD"))
    private void onUserHurt(DamageSource source, float amount, CallbackInfo info) {
        System.out.println("KEKEKEKE");
        if (!this.isInvulnerableTo(source)) {
            UserHurtCallback.EVENT.invoker().applyEffects((LivingEntity) (Object) this, source, amount);
        }
    }
}
