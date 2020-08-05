package artifacts.mixins.statuseffect.client;

import artifacts.common.item.Curio;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

	/**
	 * Cancels the night vision fading effect when wearing a curio that adds night vision as a permanent effect
	 */
	@Inject(method = "getNightVisionStrength", at = @At("RETURN"), cancellable = true)
	private static void cancelNightVisionFadeEffect(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> info) {
		if (info.getReturnValueF() != 1f) {
			CuriosApi.getCuriosHelper().getCuriosHandler(entity).ifPresent(itemHandler -> {

				for (Map.Entry<String, ICurioStacksHandler> entry : itemHandler.getCurios().entrySet()) {
					ICurioStacksHandler stacksHandler = entry.getValue();
					IDynamicStackHandler stacks = stacksHandler.getStacks();

					for (int i = 0; i < stacks.size(); i++) {
						CuriosApi.getCuriosHelper().getCurio(stacks.getStack(i)).ifPresent(curio -> {
							if (curio instanceof Curio && ((Curio) curio).getPermanentEffect() != null
									&& ((Curio) curio).getPermanentEffect().getEffectType() == StatusEffects.NIGHT_VISION) {
								info.setReturnValue(1f);
							}
						});
					}
				}
			});
		}
	}
}
