package artifacts.item.trinket;

import artifacts.client.render.model.trinket.DrinkingHatModel;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class DrinkingHatItem extends TrinketArtifactItem {

	private final Identifier texture;

	public DrinkingHatItem(Identifier texture) {
		this.texture = texture;
	}

	@Override
	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_BOTTLE_FILL;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new DrinkingHatModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return texture;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.HAT);
	}
}
