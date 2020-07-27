package artifacts.common.init;

import artifacts.Artifacts;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.IForgeRegistry;

public class SoundEvents {

    public static final SoundEvent MIMIC_HURT = new SoundEvent(new Identifier(Artifacts.MOD_ID, "mimic.hurt")).setRegistryName("mimic.hurt");
    public static final SoundEvent MIMIC_DEATH = new SoundEvent(new Identifier(Artifacts.MOD_ID, "mimic.death")).setRegistryName("mimic.death");
    public static final SoundEvent MIMIC_OPEN = new SoundEvent(new Identifier(Artifacts.MOD_ID, "mimic.open")).setRegistryName("mimic.open");
    public static final SoundEvent MIMIC_CLOSE = new SoundEvent(new Identifier(Artifacts.MOD_ID, "mimic.close")).setRegistryName("mimic.close");

    public static void register(IForgeRegistry<SoundEvent> registry) {
        registry.register(MIMIC_CLOSE);
        registry.register(MIMIC_OPEN);
        registry.register(MIMIC_HURT);
        registry.register(MIMIC_DEATH);
    }
}
