package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ObsidianSkullModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ObsidianSkullItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/obsidian_skull.png");
	private Object model;

	public ObsidianSkullItem() {
		super(new Item.Settings());
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ObsidianSkullModel getModel() {
		if (model == null) {
			model = new ObsidianSkullModel();
		}
		return (ObsidianSkullModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return super.canWearInSlot(group, slot);
	}
}
