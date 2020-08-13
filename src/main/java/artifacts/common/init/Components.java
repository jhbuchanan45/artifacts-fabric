package artifacts.common.init;

import artifacts.Awtifacts;
import artifacts.common.extensions.DwoppedItemEntityComponent;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.Identifiew;

public class Components {

	public static final ComponentType<DwoppedItemEntityComponent> DWOPPED_ITEM_ENTITY =
			ComponentRegistry.INSTANCE.registerIfAbsent(new Identifiew(Awtifacts.MODID, "dropped_item_entity"), DwoppedItemEntityComponent.class);

	public static void register() {
		EntityComponentCallback.event(ItemEntity.class).register((entity, components) -> {
			components.put(DWOPPED_ITEM_ENTITY, new DwoppedItemEntityComponent(entity));
		});
	}
}
