package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.extensions.DroppedItemEntityComponent;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.Identifier;

public class Components {

	public static final ComponentType<DroppedItemEntityComponent> DROPPED_ITEM_ENTITY =
			ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier(Artifacts.MODID, "dropped_item_entity"), DroppedItemEntityComponent.class);

	public static void register() {
		EntityComponentCallback.event(ItemEntity.class).register((entity, components) -> {
			components.put(DROPPED_ITEM_ENTITY, new DroppedItemEntityComponent(entity));
		});
	}
}
