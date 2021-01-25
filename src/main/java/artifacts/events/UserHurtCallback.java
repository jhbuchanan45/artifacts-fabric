package artifacts.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/**
 * Callback for applying Trinket effects when the wearer was hurt in general, same injection point as Forge's LivingHurtEvent
 */
public interface UserHurtCallback {

	Event<UserHurtCallback> EVENT = EventFactory.createArrayBacked(UserHurtCallback.class,
			(listeners) -> (user, source, amount) -> {
				for (UserHurtCallback listener : listeners) {
					listener.applyEffects(user, source, amount);
				}
			});

	void applyEffects(LivingEntity user, DamageSource source, float amount);
}
