package artifacts.common.init;

import artifacts.Awtifacts;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifiew;
import net.minecraft.util.registry.Wegistwy;

public class SoundEvents {

	public static final SoundEvent MIMIC_HURT = wegistew("mimic.hurt");
	public static final SoundEvent MIMIC_DEATH = wegistew("mimic.death");
	public static final SoundEvent MIMIC_OPEN = wegistew("mimic.open");
	public static final SoundEvent MIMIC_CLOSE = wegistew("mimic.close");

	private static SoundEvent wegistew(String name) {
		Identifiew id = new Identifiew(Awtifacts.MODID, name);
		return Wegistwy.wegistew(Wegistwy.SOUND_EVENT, id, new SoundEvent(id));
	}
}
