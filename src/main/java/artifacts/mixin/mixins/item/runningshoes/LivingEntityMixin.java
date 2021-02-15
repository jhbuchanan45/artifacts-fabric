package artifacts.mixin.mixins.item.runningshoes;

import artifacts.init.Items;
import artifacts.item.trinket.RunningShoesItem;
import artifacts.item.trinket.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import dev.emi.stepheightentityattribute.StepHeightEntityAttributeMain;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	@Inject(method = "setSprinting", at = @At("TAIL"))
	private void onSetSprinting(boolean sprinting, CallbackInfo info) {
		LivingEntity self = (LivingEntity) (Object) this;

		if (!TrinketsHelper.isEquipped(Items.RUNNING_SHOES, self)) {
			return;
		}

		EntityAttributeInstance movementSpeed = self.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
		EntityAttributeInstance stepHeight = self.getAttributeInstance(StepHeightEntityAttributeMain.STEP_HEIGHT);

		if (sprinting) {
			TrinketArtifactItem.addModifier(movementSpeed, RunningShoesItem.SPEED_BOOST_MODIFIER);
			TrinketArtifactItem.addModifier(stepHeight, RunningShoesItem.STEP_HEIGHT_MODIFIER);
		} else {
			TrinketArtifactItem.removeModifier(movementSpeed, RunningShoesItem.SPEED_BOOST_MODIFIER);
			TrinketArtifactItem.removeModifier(stepHeight, RunningShoesItem.STEP_HEIGHT_MODIFIER);
		}
	}
}
