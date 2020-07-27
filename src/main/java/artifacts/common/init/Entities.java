package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.entity.MimicEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.IForgeRegistry;

public class Entities {

    public static final EntityType<MimicEntity> MIMIC = EntityType.Builder.create(MimicEntity::new, SpawnGroup.MONSTER).setDimensions(14 / 16F, 14 / 16F).setTrackingRange(64).build(new Identifier(Artifacts.MODID, "mimic").toString());

    public static void register(IForgeRegistry<EntityType<?>> registry) {
        MIMIC.setRegistryName(Artifacts.MODID, "mimic");
        registry.registerAll(MIMIC);
        DefaultAttributeRegistry.put(MIMIC, MimicEntity.getAttributes().build());
    }
}
