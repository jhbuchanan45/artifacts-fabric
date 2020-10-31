package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CrystalHeartModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class CrystalHeartItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/crystal_heart.png");

	private static final EntityAttributeModifier HEALTH_BONUS = new EntityAttributeModifier(UUID.fromString("99fa0537-90b9-481a-bc76-4650987faba3"), "artifacts:crystal_heart_health_bonus", 10, EntityAttributeModifier.Operation.ADDITION);
	private Object model;

	public CrystalHeartItem() {
		super(new Item.Settings());
	}

	@Override
	public void onEquip(PlayerEntity player, ItemStack stack) {
		if (!player.world.isClient()) {
			EntityAttributeInstance health = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
			if (health != null && !health.hasModifier(HEALTH_BONUS)) {
				health.addPersistentModifier(HEALTH_BONUS);
			}
		}

		super.onEquip(player, stack);
	}

	@Override
	public void onUnequip(PlayerEntity player, ItemStack stack) {
		if (!player.world.isClient()) {
			EntityAttributeInstance health = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
			if (health != null && health.hasModifier(HEALTH_BONUS)) {
				health.removeModifier(HEALTH_BONUS);
				if (player.getHealth() > player.getMaxHealth()) {
					player.setHealth(player.getMaxHealth());
				}
			}
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected CrystalHeartModel getModel() {
		if (model == null) {
			model = new CrystalHeartModel();
		}
		return (CrystalHeartModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return super.canWearInSlot(group, slot);
	}
}
