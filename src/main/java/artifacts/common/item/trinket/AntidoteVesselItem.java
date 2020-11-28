package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.AntidoteVesselModel;
import artifacts.mixin.extensions.StatusEffectInstanceExtensions;
import artifacts.mixin.mixins.accessors.StatusEffectAccessor;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class AntidoteVesselItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/antidote_vessel.png");
	private Object model;

	public AntidoteVesselItem() {
		super(new Settings());
	}

	@Override
	public void effectTick(PlayerEntity player, ItemStack stack) {
		// Reduce duration of all negative status effects to 80
		player.getActiveStatusEffects().forEach((effect, instance) -> {
			if (!effect.isInstant() && ((StatusEffectAccessor) effect).getType() != StatusEffectType.BENEFICIAL && instance.getDuration() > 80) {
				((StatusEffectInstanceExtensions) instance).artifacts$setDuration(80);
			}
		});
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected AntidoteVesselModel getModel() {
		if (model == null) {
			model = new AntidoteVesselModel();
		}
		return (AntidoteVesselModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_BOTTLE_FILL;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}
}
