package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.HeliumFlamingoModel;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;


public class HeliumFlamingoItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/helium_flamingo.png");

	@Override
	protected BipedEntityModel<LivingEntity> createModel() {
		return new HeliumFlamingoModel();
	}

	@Override
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ENTITY_ITEM_PICKUP, 1f, 0.7f);
	}
}
