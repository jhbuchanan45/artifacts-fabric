package artifacts.mixins.statuseffect.client;

import artifacts.common.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

	/**
	 * Cancels the night vision fading effect when wearing a trinket that adds night vision as a permanent effect
	 */
	@Inject(method = "getNightVisionStrength", at = @At("RETURN"), cancellable = true)
	private static void cancelNightVisionFadeEffect(LivingEntity entity, float tickDelta, CallbackInfoReturnable<Float> info) {
		if (info.getReturnValueF() != 1f && entity instanceof PlayerEntity) {
			Inventory inventory = TrinketsApi.getTrinketsInventory((PlayerEntity) entity);

			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.getStack(i).getItem();

				if (item instanceof TrinketArtifactItem) {
					StatusEffectInstance effect = ((TrinketArtifactItem) item).getPermanentEffect();

					if (effect != null && effect.getEffectType() == StatusEffects.NIGHT_VISION) {
						info.setReturnValue(1.0f);
					}
				}
			}
		}
	}
}
