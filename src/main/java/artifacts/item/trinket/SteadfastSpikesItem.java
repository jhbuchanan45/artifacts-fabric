package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.ClawsModel;
import artifacts.client.render.model.trinket.GloveModel;
import artifacts.client.render.model.trinket.SnorkelModel;
import artifacts.client.render.model.trinket.SteadfastSpikesModel;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class SteadfastSpikesItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/steadfast_spikes.png");

	@Override
	protected Multimap<EntityAttribute, EntityAttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.applyModifiers(group, slot, uuid, stack);
		EntityAttributeModifier modifier = new EntityAttributeModifier(uuid,
				Artifacts.id("steadfast_spikes_knockback_resistance").toString(),
				1, EntityAttributeModifier.Operation.ADDITION);
		modifiers.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, modifier);
		return modifiers;
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(SteadfastSpikesModel.getTexturedGloveData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new SteadfastSpikesModel(root, false);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

}
