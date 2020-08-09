package artifacts.mixins.event;

import artifacts.common.events.UserAttackedCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {

    @Inject(method = "onUserDamaged", at = @At("HEAD"))
    private static void applyPendantEffect(LivingEntity entity, Entity attacker, CallbackInfo info) {
        UserAttackedCallback.EVENT.invoker().applyEffects(entity, attacker, entity.getRandom());
    }
}
