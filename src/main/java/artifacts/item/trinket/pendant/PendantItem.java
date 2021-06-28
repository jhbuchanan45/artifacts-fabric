package artifacts.item.trinket.pendant;

import artifacts.client.render.model.trinket.PendantModel;
import artifacts.events.LivingEntityAttackedCallback;
import artifacts.item.trinket.TrinketArtifactItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public abstract class PendantItem extends TrinketArtifactItem {

	private final Identifier texture;

	public PendantItem(Identifier texture, LivingEntityAttackedCallback callback) {
		this.texture = texture;
		LivingEntityAttackedCallback.EVENT.register(callback);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new PendantModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return texture;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals("chest") && slot.equals(Slots.NECKLACE);
	}
}
