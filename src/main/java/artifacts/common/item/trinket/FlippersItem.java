package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.FlippersModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class FlippersItem extends TrinketArtifactItem {

	public static final int SWIM_SPEED_MULTIPLIER = 2;
	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/flippers.png");
	private Object model;


	public FlippersItem() {
		super(new Item.Settings());
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected FlippersModel getModel() {
		if (model == null) {
			model = new FlippersModel();
		}
		return (FlippersModel) model;
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
