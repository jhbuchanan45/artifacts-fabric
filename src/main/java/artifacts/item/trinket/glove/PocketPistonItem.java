package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class PocketPistonItem extends GloveItem {

	private static final Identifier TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/pocket_piston_default.png");
	private static final Identifier TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/pocket_piston_slim.png");

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getSlimTexture() {
		return TEXTURE_SLIM;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE_DEFAULT;
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.BLOCK_PISTON_EXTEND);
	}
}
