package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ScarfModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ScarfOfInvisibilityItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/scarf_of_invisibility.png");
	private Object model;

	public ScarfOfInvisibilityItem() {
		super(new Item.Settings());
	}

	@Override
	public StatusEffectInstance getPermanentEffect() {
		return new StatusEffectInstance(StatusEffects.INVISIBILITY, 20, 0, true, false);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ScarfModel getModel() {
		if (model == null) {
			model = new ScarfModel(RenderLayer::getEntityTranslucent);
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
