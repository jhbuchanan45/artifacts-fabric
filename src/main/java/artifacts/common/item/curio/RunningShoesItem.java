package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.RunningShoesModel;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import dev.emi.stepheightentityattribute.StepHeightEntityAttributeMain;
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

public class RunningShoesItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/running_shoes.png");

	private static final EntityAttributeModifier SPEED_BOOST_MODIFIER = new EntityAttributeModifier(UUID.fromString("ac7ab816-2b08-46b6-879d-e5dea34ff305"), "artifacts:running_shoes_movement_speed", 0.4, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final EntityAttributeModifier STEP_HEIGHT_MODIFIER = new EntityAttributeModifier(UUID.fromString("7e97cede-a343-411f-b465-14cdf6df3666"), "artifacts:running_shoes_step_height", .5, EntityAttributeModifier.Operation.ADDITION);

	public RunningShoesItem() {
		super(new Item.Settings());
	}

	@Override
	// TODO: hook into sprinting method instead
	protected ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			@Override
			@SuppressWarnings("ConstantConditions")
			public void curioTick(String identifier, int index, LivingEntity livingEntity) {
				EntityAttributeInstance movementSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
				EntityAttributeInstance stepHeight = livingEntity.getAttributeInstance(StepHeightEntityAttributeMain.STEP_HEIGHT);

				if (livingEntity.isSprinting()) {
					addModifier(movementSpeed, SPEED_BOOST_MODIFIER);
					addModifier(stepHeight, STEP_HEIGHT_MODIFIER);
				} else {
					removeModifier(movementSpeed, SPEED_BOOST_MODIFIER);
					removeModifier(stepHeight, STEP_HEIGHT_MODIFIER);
				}
			}

			@Override
			@SuppressWarnings("ConstantConditions")
			public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
				EntityAttributeInstance movementSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
				EntityAttributeInstance stepHeight = livingEntity.getAttributeInstance(StepHeightEntityAttributeMain.STEP_HEIGHT);

				removeModifier(movementSpeed, SPEED_BOOST_MODIFIER);
				removeModifier(stepHeight, STEP_HEIGHT_MODIFIER);
			}

			private void addModifier(EntityAttributeInstance instance, EntityAttributeModifier modifier) {
				if (!instance.hasModifier(modifier)) {
					instance.addTemporaryModifier(modifier);
				}
			}

			private void removeModifier(EntityAttributeInstance instance, EntityAttributeModifier modifier) {
				if (instance.hasModifier(modifier)) {
					instance.removeModifier(modifier);
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
			protected RunningShoesModel getModel() {
				if (model == null) {
					model = new RunningShoesModel();
				}
				return (RunningShoesModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return TEXTURE;
			}
		};
	}
}
