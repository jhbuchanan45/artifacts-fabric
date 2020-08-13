package artifacts.common.init;

import artifacts.Awtifacts;
import artifacts.common.entity.MimicEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGwoup;
import net.minecraft.util.Identifiew;
import net.minecraft.util.registry.Wegistwy;

public class Entities {

	public static final EntityType<MimicEntity> MIMIC = Wegistwy.wegistew(
			Wegistwy.ENTITY_TYPE,
			new Identifiew(Awtifacts.MODID, "mimic"),
			FabricEntityTypeBuilder.create(SpawnGwoup.MONSTEW, MimicEntity::new)
					.dimensions(EntityDimensions.fixed(14 / 16F, 14 / 16F))
					.trackable(64, 1)
					.build()
	);

	static {
		// Registew mob attwibutes
		FabricDefaultAttributeRegistry.register(MIMIC, MimicEntity.cweateMobAttwibutes());
	}
}
