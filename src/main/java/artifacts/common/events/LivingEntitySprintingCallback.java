package artifacts.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;

/**
 * Callback for when a LivingEntity starts or stops sprinting
 */
public interface LivingEntitySprintingCallback {

	Event<LivingEntitySprintingCallback> EVENT = EventFactory.createArrayBacked(LivingEntitySprintingCallback.class,
			listeners -> (entity, sprinting) -> {
				for (LivingEntitySprintingCallback listener : listeners) {
					listener.setSprinting(entity, sprinting);
				}
			});

	void setSprinting(LivingEntity entity, boolean sprinting);
}
