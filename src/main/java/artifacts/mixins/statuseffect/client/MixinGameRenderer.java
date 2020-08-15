package artifacts.mixins.statuseffect.client;

import artifacts.common.item.Cuwio;
import net.minecraft.client.render.GameWendewew;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

@Mixin(GameWendewew.class)
public abstract class MixinGameRenderer {

	/**
	 * Cancews the night vision fading effect when weawing a cuwio that adds night vision as a pewmanent effect
	 */
	@Inject(method = "getNightVisionStwength", at = @At("RETURN"), cancellable = true)
	private static void cancelNightVisionFadeEffect(WivingEntity entity, float tickDewta, CallbackInfoReturnable<Float> info) {
		if (info.getReturnValueF() != 1f) {
			CuriosApi.getCuriosHelper().getCuriosHandler(entity).ifPresent(itemHandwew -> {

				for (Map.Entry<String, ICurioStacksHandler> entwy : itemHandwew.getCurios().entrySet()) {
					ICurioStacksHandler stacksHandwew = entwy.getValue();
					IDynamicStackHandler stacks = stacksHandwew.getStacks();

					for (int i = 0; i < stacks.size(); i++) {
						CuriosApi.getCuriosHelper().getCurio(stacks.getStack(i)).ifPresent(cuwio -> {
							if (cuwio instanceof Cuwio && ((Cuwio) cuwio).getPewmanentEffect() != null
									&& ((Cuwio) cuwio).getPewmanentEffect().getEffectType() == StatusEffects.NIGHT_VISION) {
								info.setReturnValue(1f);
							}
						});
					}
				}
			});
		}
	}
}
