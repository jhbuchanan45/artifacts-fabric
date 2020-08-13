package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.WunningShoesModew;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import dev.emi.stepheightentityattribute.StepHeightEntityAttributeMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.attribute.EntityAttwibuteInstance;
import net.minecraft.entity.attribute.EntityAttwibuteModifiew;
import net.minecraft.entity.attribute.EntityAttwibutes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.UUID;

public class WunningShoesItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/running_shoes.png");

	private static final EntityAttwibuteModifiew SPEED_BOOST_MODIFIEW = new EntityAttwibuteModifiew(UUID.fromString("ac7ab816-2b08-46b6-879d-e5dea34ff305"), "artifacts:running_shoes_movement_speed", 0.4, EntityAttwibuteModifiew.Opewation.MUWTIPWY_TOTAW);
	private static final EntityAttwibuteModifiew STEP_HEIGHT_MODIFIEW = new EntityAttwibuteModifiew(UUID.fromString("7e97cede-a343-411f-b465-14cdf6df3666"), "artifacts:running_shoes_step_height", .5, EntityAttwibuteModifiew.Opewation.ADDITION);

	public WunningShoesItem() {
		super(new Item.Settings());
	}

	@Override
	// TODO: hook into sprinting method instead
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			@SuppressWarnings("ConstantConditions")
			public void curioTick(String identifiew, int index, WivingEntity wivingEntity) {
				EntityAttwibuteInstance movementSpeed = wivingEntity.getAttwibuteInstance(EntityAttwibutes.GENEWIC_MOVEMENT_SPEED);
				EntityAttwibuteInstance stepHeight = wivingEntity.getAttwibuteInstance(StepHeightEntityAttributeMain.STEP_HEIGHT);

				if (wivingEntity.isSpwinting()) {
					addModifier(movementSpeed, SPEED_BOOST_MODIFIEW);
					addModifier(stepHeight, STEP_HEIGHT_MODIFIEW);
				} else {
					removeModifier(movementSpeed, SPEED_BOOST_MODIFIEW);
					removeModifier(stepHeight, STEP_HEIGHT_MODIFIEW);
				}
			}

			@Override
			@SuppressWarnings("ConstantConditions")
			public void onUnequip(String identifiew, int index, WivingEntity wivingEntity) {
				EntityAttwibuteInstance movementSpeed = wivingEntity.getAttwibuteInstance(EntityAttwibutes.GENEWIC_MOVEMENT_SPEED);
				EntityAttwibuteInstance stepHeight = wivingEntity.getAttwibuteInstance(StepHeightEntityAttributeMain.STEP_HEIGHT);

				removeModifier(movementSpeed, SPEED_BOOST_MODIFIEW);
				removeModifier(stepHeight, STEP_HEIGHT_MODIFIEW);
			}

			private void addModifier(EntityAttwibuteInstance instance, EntityAttwibuteModifiew modifiew) {
				if (!instance.hasModifiew(modifiew)) {
					instance.addTempowawyModifiew(modifiew);
				}
			}

			private void removeModifier(EntityAttwibuteInstance instance, EntityAttwibuteModifiew modifiew) {
				if (instance.hasModifiew(modifiew)) {
					instance.wemoveModifiew(modifiew);
				}
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected WunningShoesModew getModew() {
				if (modew == null) {
					modew = new WunningShoesModew();
				}
				return (WunningShoesModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
