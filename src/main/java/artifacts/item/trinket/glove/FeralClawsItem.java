package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.ClawsModel;
import artifacts.client.render.model.trinket.GloveModel;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class FeralClawsItem extends GloveItem {

	private static final Identifier TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/feral_claws_default.png");
	private static final Identifier TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/feral_claws_slim.png");

	@Override
	protected Multimap<EntityAttribute, EntityAttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<EntityAttribute, EntityAttributeModifier> result = super.applyModifiers(group, slot, uuid, stack);
		EntityAttributeModifier modifier = new EntityAttributeModifier(uuid,
				Artifacts.id("feral_claws_attack_speed").toString(),
				1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
		result.put(EntityAttributes.GENERIC_ATTACK_SPEED, modifier);
		return result;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected GloveModel createModel(boolean smallArms) {
		return new ClawsModel(smallArms);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE_DEFAULT;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getSlimTexture() {
		return TEXTURE_SLIM;
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE);
	}
}
