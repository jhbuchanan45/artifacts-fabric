package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.components.DroppedItemEntityComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.Identifier;

public class Components implements EntityComponentInitializer {

	public static final ComponentKey<DroppedItemEntityComponent> DROPPED_ITEM_ENTITY =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Artifacts.MODID, "dropped_item_entity"), DroppedItemEntityComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(ItemEntity.class, DROPPED_ITEM_ENTITY, entity -> new DroppedItemEntityComponent());
	}
}
