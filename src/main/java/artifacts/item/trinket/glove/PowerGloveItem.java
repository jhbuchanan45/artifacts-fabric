package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class PowerGloveItem extends GloveItem {

	private static final Identifier TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/power_glove_default.png");
	private static final Identifier TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/power_glove_slim.png");

	@Override
	protected Multimap<EntityAttribute, EntityAttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<EntityAttribute, EntityAttributeModifier> result = super.applyModifiers(group, slot, uuid, stack);
		EntityAttributeModifier modifier = new EntityAttributeModifier(uuid,
				Artifacts.id("power_glove_attack_damage").toString(),
				4, EntityAttributeModifier.Operation.ADDITION);
		result.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, modifier);
		return result;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getSlimTexture() {
		return TEXTURE_SLIM;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE_DEFAULT;
	}
}
