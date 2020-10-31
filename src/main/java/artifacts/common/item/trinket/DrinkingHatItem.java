package artifacts.common.item.trinket;

import artifacts.client.render.model.curio.DrinkingHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class DrinkingHatItem extends TrinketArtifactItem {

	private final Identifier texture;
	private Object model;

	public DrinkingHatItem(Identifier texture) {
		super(new Item.Settings());
		this.texture = texture;
	}

	@Override
	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_BOTTLE_FILL;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected DrinkingHatModel getModel() {
		if (model == null) {
			model = new DrinkingHatModel();
		}
		return (DrinkingHatModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return texture;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return super.canWearInSlot(group, slot);
	}
}
