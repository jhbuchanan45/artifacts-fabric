package artifacts.common.item.trinket;

import artifacts.Artifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class PocketPistonItem extends GloveItem {

	private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MODID, "textures/entity/trinket/pocket_piston_default.png");
	private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MODID, "textures/entity/trinket/pocket_piston_slim.png");

	public PocketPistonItem() {
		super(new Item.Settings());
	}

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
	protected SoundEvent getEquipSound() {
		return SoundEvents.BLOCK_PISTON_EXTEND;
	}
}
