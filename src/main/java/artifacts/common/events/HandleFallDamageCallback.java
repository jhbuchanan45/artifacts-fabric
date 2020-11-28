package artifacts.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

/**
 * Callback for when fall damage gets calculated for a LivingEntity
 *
 * ActionResult.SUCCESS and ActionResult.CONSUME: don't cancel fall damage, cancel event immediately
 * ActionResult.PASS: don't cancel fall damage, but check other listeners first
 * ActionResult.FAIL: cancel fall damage, cancel event immediately
 */
public interface HandleFallDamageCallback {

	Event<HandleFallDamageCallback> EVENT = EventFactory.createArrayBacked(HandleFallDamageCallback.class,
			listeners -> (entity, fallDistance, damageMultiplier) -> {
				for (HandleFallDamageCallback listener : listeners) {
					ActionResult result = listener.handle(entity, fallDistance, damageMultiplier);

					if (result != ActionResult.PASS) {
						return result;
					}
				}

				return ActionResult.PASS;
			});

	ActionResult handle(LivingEntity entity, float fallDistance, float damageMultiplier);
}
