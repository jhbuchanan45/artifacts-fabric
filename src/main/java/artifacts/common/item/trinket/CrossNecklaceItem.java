package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CrossNecklaceModel;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class CrossNecklaceItem extends TrinketArtifactItem {

	public static final double HURT_RESISTANCE_MULTIPLIER = 3; // Hurt invuln is multiplied by this factor
	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/cross_necklace.png");
	private Object model;

	public CrossNecklaceItem() {
		super(new Item.Settings());
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected CrossNecklaceModel getModel() {
		if (model == null) {
			model = new CrossNecklaceModel();
		}
		return (CrossNecklaceModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}
}
