package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.components.BooleanComponent;
import artifacts.common.components.TrinketEnabledComponent;
import artifacts.common.item.trinket.TrinketArtifactItem;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.Identifier;

public class Components implements EntityComponentInitializer, ItemComponentInitializer {

	public static final ComponentKey<BooleanComponent> DROPPED_ITEM_ENTITY =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Artifacts.MODID, "dropped_item_entity"),
					BooleanComponent.class);
	public static final ComponentKey<TrinketEnabledComponent> TRINKET_ENABLED =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Artifacts.MODID, "trinket_enabled"),
					TrinketEnabledComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(ItemEntity.class, DROPPED_ITEM_ENTITY, entity -> new BooleanComponent("wasDropped"));
	}

	@Override
	public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
		registry.registerFor(item -> item instanceof TrinketArtifactItem, TRINKET_ENABLED, TrinketEnabledComponent::new);
	}
}
