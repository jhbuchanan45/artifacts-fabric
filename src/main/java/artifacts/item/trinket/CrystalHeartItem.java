package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CrossNecklaceModel;
import artifacts.client.render.model.trinket.CrystalHeartModel;
import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class CrystalHeartItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/crystal_heart.png");

	private static final EntityAttributeModifier HEALTH_BONUS = new EntityAttributeModifier(UUID.fromString("99fa0537-90b9-481a-bc76-4650987faba3"),
			"artifacts:crystal_heart_health_bonus", 10, EntityAttributeModifier.Operation.ADDITION);

	@Override
	public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
		if (!entity.world.isClient()) {
			EntityAttributeInstance health = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
			if (health != null && !health.hasModifier(HEALTH_BONUS)) {
				health.addPersistentModifier(HEALTH_BONUS);
			}
		}

		super.onEquip(stack, slot, entity);
	}

	@Override
	public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
		if (!entity.world.isClient()) {
			EntityAttributeInstance health = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
			if (health != null && health.hasModifier(HEALTH_BONUS)) {
				health.removeModifier(HEALTH_BONUS);
				if (entity.getHealth() > entity.getMaxHealth()) {
					entity.setHealth(entity.getMaxHealth());
				}
			}
		}
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(CrystalHeartModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new CrystalHeartModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}
}
