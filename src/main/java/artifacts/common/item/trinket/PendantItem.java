package artifacts.common.item.trinket;

import artifacts.client.render.model.trinket.PendantModel;
import artifacts.common.events.UserAttackedCallback;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public abstract class PendantItem extends TrinketArtifactItem {

	private final Identifier texture;
	private Object model;

	public PendantItem(Identifier texture, UserAttackedCallback callback) {
		super(new Item.Settings());
		this.texture = texture;
		UserAttackedCallback.EVENT.register(callback);
	}

	@Override
	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected PendantModel getModel() {
		if (model == null) {
			model = new PendantModel();
		}
		return (PendantModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return texture;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.NECKLACE);
	}
}
