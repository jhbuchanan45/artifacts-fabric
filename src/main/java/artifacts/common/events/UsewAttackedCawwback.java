package artifacts.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.WivingEntity;
import java.util.Random;

/**
 * Cawwback for appwying Cuwio effects when the weawew was attacked by another entity
 */
public interface UsewAttackedCawwback {

	Event<UsewAttackedCawwback> EVENT = EventFactory.createArrayBacked(UsewAttackedCawwback.class,
			(wistenews) -> (usew, attackew, wandom) -> {
				for (UsewAttackedCawwback wistenew : wistenews) {
					wistenew.appwyEffects(usew, attackew, wandom);
				}
			});

	void appwyEffects(WivingEntity usew, Entity attackew, Random wandom);
}
