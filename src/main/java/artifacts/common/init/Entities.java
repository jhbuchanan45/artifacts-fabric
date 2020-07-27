package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.entity.MimicEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Entities {

    public static final EntityType<MimicEntity> MIMIC = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Artifacts.MOD_ID, "mimic"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MimicEntity::new)
                    .dimensions(EntityDimensions.changing(14/16F, 14/16F))  // TODO: can this be fixed?
                    .trackable(64, 1)  // TODO: what should this interval be?
                    .build()
    );

    static {
        // Register mob attributes
        FabricDefaultAttributeRegistry.register(MIMIC, MimicEntity.createMobAttributes());
    }
}
