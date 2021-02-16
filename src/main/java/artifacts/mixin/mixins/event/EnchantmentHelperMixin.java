package artifacts.mixin.mixins.event;

import artifacts.events.LivingEntityAttackedCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

	@Inject(method = "onUserDamaged", at = @At("HEAD"))
	private static void onUserAttacked(LivingEntity entity, Entity attacker, CallbackInfo info) {
		LivingEntityAttackedCallback.EVENT.invoker().attack(entity, attacker, entity.getRandom());
	}
}
