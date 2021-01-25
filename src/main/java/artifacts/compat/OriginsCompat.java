package artifacts.compat;

import artifacts.item.UmbrellaItem;
import artifacts.mixin.mixins.compat.origins.ConditionFactoryAccessor;
import io.github.apace100.origins.Origins;
import io.github.apace100.origins.power.factory.condition.ConditionFactory;
import io.github.apace100.origins.registry.ModRegistries;
import io.github.apace100.origins.util.SerializableData;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;

public class OriginsCompat implements Runnable {

	@Override
	public void run() {
		RegistryEntryAddedCallback.event(ModRegistries.PLAYER_CONDITION).register(OriginsCompat::playerConditionAdded);
	}

	private static void playerConditionAdded(int rawId, Identifier id, ConditionFactory<PlayerEntity> conditionFactory) {
		// Held-up umbrella blocks origins:exposed_to_sun condition
		if (conditionFactory.getSerializerId().equals(new Identifier(Origins.MODID, "exposed_to_sun"))) {
			//noinspection unchecked
			ConditionFactoryAccessor<PlayerEntity> conditionAccess = (ConditionFactoryAccessor<PlayerEntity>) conditionFactory;
			BiFunction<SerializableData.Instance, PlayerEntity, Boolean> origCondition = conditionAccess.getCondition();

			// Wrapper around original condition
			conditionAccess.setCondition((instance, player) -> origCondition.apply(instance, player)
					&& !UmbrellaItem.isHeldUpInEitherHand(player));
		}
	}
}
