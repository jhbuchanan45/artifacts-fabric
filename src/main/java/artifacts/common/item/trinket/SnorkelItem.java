package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.SnorkelModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class SnorkelItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/snorkel.png");
	private Object model;

	public SnorkelItem() {
		super(new Item.Settings());
	}

	@Override
	public StatusEffectInstance getPermanentEffect() {
		return new StatusEffectInstance(StatusEffects.WATER_BREATHING, 20, 0, true, false);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected SnorkelModel getModel() {
		if (model == null) {
			model = new SnorkelModel();
		}
		return (SnorkelModel) model;
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
