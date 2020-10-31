package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.extensions.DroppedItemEntityComponent;
import artifacts.common.item.trinket.TrinketArtifactItem;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosComponent;

public class Components implements EntityComponentInitializer, ItemComponentInitializer {

	public static final ComponentKey<DroppedItemEntityComponent> DROPPED_ITEM_ENTITY =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Artifacts.MODID, "dropped_item_entity"), DroppedItemEntityComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(ItemEntity.class, DROPPED_ITEM_ENTITY, entity -> new DroppedItemEntityComponent());
	}

	@Override
	public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
		registry.registerFor((Item item) -> item instanceof TrinketArtifactItem, CuriosComponent.ITEM,
				stack -> ((TrinketArtifactItem)stack.getItem()).attachCurio(stack));
	}
}
