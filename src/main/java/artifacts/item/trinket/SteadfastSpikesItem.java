package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.SteadfastSpikesModel;
import artifacts.trinkets.Slots;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class SteadfastSpikesItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/steadfast_spikes.png");

	@Override
	protected Multimap<EntityAttribute, EntityAttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.applyModifiers(group, slot, uuid, stack);
		EntityAttributeModifier modifier = new EntityAttributeModifier(uuid,
				new Identifier(Artifacts.MODID, "steadfast_spikes_knockback_resistance").toString(),
				1, EntityAttributeModifier.Operation.ADDITION);
		modifiers.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, modifier);
		return modifiers;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new SteadfastSpikesModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
