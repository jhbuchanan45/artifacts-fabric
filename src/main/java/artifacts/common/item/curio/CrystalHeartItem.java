package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.CrystalHeartModel;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.UUID;

public class CrystalHeartItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/crystal_heart.png");

	private static final EntityAttributeModifier HEALTH_BONUS = new EntityAttributeModifier(UUID.fromString("99fa0537-90b9-481a-bc76-4650987faba3"), "artifacts:crystal_heart_health_bonus", 10, EntityAttributeModifier.Operation.ADDITION);

	public CrystalHeartItem() {
		super(new Item.Settings());
	}

	@Override
	public ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			@Override
			public void onEquip(String identifier, int index, LivingEntity entity) {
				if (!entity.world.isClient()) {
					EntityAttributeInstance health = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
					if (health != null && !health.hasModifier(HEALTH_BONUS)) {
						health.addPersistentModifier(HEALTH_BONUS);
					}
				}
			}

			@Override
			public void onUnequip(String identifier, int index, LivingEntity entity) {
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
		};
	}

	@Override
	protected IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableCurio() {
			private Object model;

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
		};
	}
}
