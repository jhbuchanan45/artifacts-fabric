package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.SteadfastSpikesModel;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.UUID;

public class SteadfastSpikesItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/steadfast_spikes.png");

	private static final EntityAttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = new EntityAttributeModifier(UUID.fromString("2aa3958f-49f5-47ba-a707-a4679ad7ff17"), "artifacts:steadfast_spikes_knockback_resistance", 1, EntityAttributeModifier.Operation.ADDITION);
	private Object model;

	public SteadfastSpikesItem() {
		super(new Item.Settings());
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getTrinketModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<EntityAttribute, EntityAttributeModifier> result = super.getTrinketModifiers(group, slot, uuid, stack);
		result.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, KNOCKBACK_RESISTANCE_MODIFIER);
		return result;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected SteadfastSpikesModel getModel() {
		if (model == null) {
			model = new SteadfastSpikesModel();
		}
		return (SteadfastSpikesModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return false;
	}
}
