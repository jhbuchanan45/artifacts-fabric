package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.VillagerHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class VillagerHatItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/villager_hat.png");
	private Object model;

	public VillagerHatItem() {
		super(new Item.Settings());
	}

	@Override
	public StatusEffectInstance getPermanentEffect() {
		return new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 20, 1, true, false);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected VillagerHatModel getModel() {
		if (model == null) {
			model = new VillagerHatModel();
		}
		return (VillagerHatModel) model;
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
