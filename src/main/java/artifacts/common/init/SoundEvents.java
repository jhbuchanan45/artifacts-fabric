package artifacts.common.init;

import artifacts.Artifacts;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundEvents {

	public static final SoundEvent MIMIC_HURT = register("mimic.hurt");
	public static final SoundEvent MIMIC_DEATH = register("mimic.death");
	public static final SoundEvent MIMIC_OPEN = register("mimic.open");
	public static final SoundEvent MIMIC_CLOSE = register("mimic.close");
	public static final SoundEvent FART = register("fart");

	private static SoundEvent register(String name) {
		Identifier id = new Identifier(Artifacts.MODID, name);
		return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}
}
