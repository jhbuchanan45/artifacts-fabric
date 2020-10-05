package artifacts.mixins.statuseffect.client;

import artifacts.common.item.Curio;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

	/**
	 * Show the effect as permanent, which normally only happens if the duration is >= 32767
	 * Doing it here makes sure it is set to permanent everytime the server sent an update packet
	 */
	@Inject(method = "applyStatusEffect", at = @At("HEAD"))
	private void showStatusEffectPermanent(StatusEffectInstance effect, CallbackInfo info) {
		CuriosApi.getCuriosHelper().getCuriosHandler((LivingEntity)(Object) this).ifPresent(itemHandler -> {

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
		});
	}
}
