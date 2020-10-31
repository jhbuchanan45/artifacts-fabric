package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ScarfModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class LuckyScarfItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/lucky_scarf.png");
	private Object model;

	public LuckyScarfItem() {
		super(new Item.Settings());
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ScarfModel getModel() {
		if (model == null) {
			model = new ScarfModel();
		}
		return (ScarfModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return false;
	}
}
