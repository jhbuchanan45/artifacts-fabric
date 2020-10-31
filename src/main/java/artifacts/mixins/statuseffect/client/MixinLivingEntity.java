package artifacts.mixins.statuseffect.client;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

	/**
	 * Show the effect as permanent, which normally only happens if the duration is >= 32767
	 * Doing it here makes sure it is set to permanent everytime the server sent an update packet
	 */
	@Inject(method = "applyStatusEffect", at = @At("HEAD"))
	private void showStatusEffectPermanent(StatusEffectInstance effect, CallbackInfo info) {
		// TODO: Port to Trinkets
		/*CuriosApi.getCuriosHelper().getCuriosHandler((LivingEntity)(Object) this).ifPresent(itemHandler -> {

			for (Map.Entry<String, ICurioStacksHandler> entry : itemHandler.getCurios().entrySet()) {
				ICurioStacksHandler stacksHandler = entry.getValue();
				IDynamicStackHandler stacks = stacksHandler.getStacks();

				for (int i = 0; i < stacks.size(); i++) {
					CuriosApi.getCuriosHelper().getCurio(stacks.getStack(i)).ifPresent(curio -> {
						if (curio instanceof Curio && ((Curio) curio).getPermanentEffect() != null
								&& ((Curio) curio).getPermanentEffect().getEffectType() == effect.getEffectType()) {
							effect.setPermanent(true);
						}
					});
				}
			}
		});*/
	}
}
