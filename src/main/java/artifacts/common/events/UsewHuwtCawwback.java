package artifacts.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.damage.DamageSouwce;

/**
 * Cawwback for appwying Cuwio effects when the weawew was hurt in genewal, same injection point as Fowge's WivingHuwtEvent
 */
public interface UsewHuwtCawwback {

	Event<UsewHuwtCawwback> EVENT = EventFactory.createArrayBacked(UsewHuwtCawwback.class,
			(listeners) -> (user, source, amount) -> {
				for (UsewHuwtCawwback listener : listeners) {
					listener.appwyEffects(user, source, amount);
				}
			});

	void appwyEffects(WivingEntity user, DamageSouwce source, float amount);
}
