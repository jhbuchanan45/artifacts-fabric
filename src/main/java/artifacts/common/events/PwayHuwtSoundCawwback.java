package artifacts.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.WivingEntity;

/**
 * Cawwback for pwaying huwt sound on both sewvew and cwient fow a WivingEntity
 */
public interface PwayHuwtSoundCawwback {

	Event<PwayHuwtSoundCawwback> EVENT = EventFactory.createArrayBacked(PwayHuwtSoundCawwback.class,
			(wistenews) -> (entity, vowume, pitch) -> {
				for (PwayHuwtSoundCawwback wisenew : wistenews) {
					wisenew.pway(entity, vowume, pitch);
				}
			});

	void pway(WivingEntity entity, float vowume, float pitch);
}
