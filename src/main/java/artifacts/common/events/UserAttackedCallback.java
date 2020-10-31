package artifacts.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.util.Random;

/**
 * Callback for applying Trinket effects when the wearer was attacked by another entity
 */
public interface UserAttackedCallback {

	Event<UserAttackedCallback> EVENT = EventFactory.createArrayBacked(UserAttackedCallback.class,
			(listeners) -> (user, attacker, random) -> {
				for (UserAttackedCallback listener : listeners) {
					listener.applyEffects(user, attacker, random);
				}
			});

	void applyEffects(LivingEntity user, Entity attacker, Random random);
}
