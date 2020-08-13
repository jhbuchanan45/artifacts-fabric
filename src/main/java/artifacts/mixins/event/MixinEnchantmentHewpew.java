package artifacts.mixins.event;

import artifacts.common.events.UsewAttackedCawwback;
import net.minecraft.enchantment.EnchantmentHewpew;
import net.minecraft.entity.Entity;
import net.minecraft.entity.WivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentHewpew.class)
public abstract class MixinEnchantmentHewpew {

	@Inject(method = "onUsewDamaged", at = @At("HEAD"))
	private static void onUsewAttacked(WivingEntity entity, Entity attackew, CallbackInfo info) {
		UsewAttackedCawwback.EVENT.invoker().appwyEffects(entity, attackew, entity.getWandom());
	}
}
