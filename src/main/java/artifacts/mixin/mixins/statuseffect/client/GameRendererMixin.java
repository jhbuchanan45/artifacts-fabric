package artifacts.mixin.mixins.statuseffect.client;

import artifacts.item.trinket.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

	/**
	 * Cancels the night vision fading effect when wearing a trinket that adds night vision as a permanent effect
	 */
	@Inject(method = "getNightVisionStrength", at = @At("RETURN"), cancellable = true)
	private static void cancelNightVisionFadeEffect(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> info) {
		if (info.getReturnValueF() != 1f) {
			TrinketsHelper.getAllEquipped(entity).stream()
					.map(stack -> Optional.ofNullable(((TrinketArtifactItem) stack.getItem()).getPermanentEffect()))
					.filter(effect -> effect.isPresent() && effect.get().getEffectType() == StatusEffects.NIGHT_VISION)
					.findAny()
					.ifPresent(effect -> info.setReturnValue(1.0f));
		}
	}
}
