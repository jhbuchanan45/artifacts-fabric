package artifacts.mixin.mixins.item.umbrella.client;

import artifacts.common.init.Items;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class MixinBipedEntityModel<T extends LivingEntity> {

	@Shadow public ModelPart rightArm;
	@Shadow public ModelPart leftArm;

	// Target is unresolved because method owner is a generic T
	// Seems to work fine, but has failed to apply once or twice in dev (in a fresh runtime)
	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(method = "setAngles", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getMainArm()Lnet/minecraft/util/Arm;"))
	private void reduceHandSwing(T entity, float f, float g, float h, float i, float j, CallbackInfo info) {
		if (!(entity.isUsingItem() && !entity.getActiveItem().isEmpty() && entity.getActiveItem().getItem().getUseAction(entity.getActiveItem()) == UseAction.BLOCK)) {
			// Check if umbrella is held in main or offhand
			boolean isHoldingOffHand = entity.getOffHandStack().getItem() == Items.UMBRELLA;
			boolean isHoldingMainHand = entity.getMainHandStack().getItem() == Items.UMBRELLA;
			Arm mainArm = MinecraftClient.getInstance().options.mainArm;

			// Handle right hand
			if ((isHoldingMainHand && mainArm == Arm.RIGHT) || (isHoldingOffHand && mainArm == Arm.LEFT)) {
				this.rightArm.pitch /= 8;
			}
			// Handle left hand
			if ((isHoldingMainHand && mainArm == Arm.LEFT) || (isHoldingOffHand && mainArm == Arm.RIGHT)) {
				this.leftArm.pitch /= 8;
			}
		}
	}
}
