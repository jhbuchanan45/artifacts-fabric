package artifacts.mixin.mixins.item.umbrella.client;

import artifacts.common.init.Items;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> {

	@Shadow protected M model;

	protected MixinLivingEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Inject(method = "render", at = @At("HEAD"))
	private void renderUmbrella(T entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo info) {
		// Check if entity is not blocking with an item (shield)
		if (this.model instanceof BipedEntityModel) {
			//noinspection rawtypes
			BipedEntityModel model = (BipedEntityModel) this.model;

			boolean isBlocking = entity.isUsingItem() && !entity.getActiveItem().isEmpty() && entity.getActiveItem().getItem().getUseAction(entity.getActiveItem()) == UseAction.BLOCK;

			// Check if umbrella is held in main or offhand
			boolean isHoldingMainHand = entity.getMainHandStack().getItem() == Items.UMBRELLA;
			boolean isHoldingOffHand = entity.getOffHandStack().getItem() == Items.UMBRELLA;
			boolean mainHandActive = entity.getActiveHand() == Hand.MAIN_HAND;
			Arm mainArm = MinecraftClient.getInstance().options.mainArm;

			// Handle right hand
			boolean heldInRightHand = isHoldingMainHand && mainArm == Arm.RIGHT || isHoldingOffHand && mainArm == Arm.LEFT;
			boolean rightHandActive = isBlocking && (mainHandActive && mainArm == Arm.RIGHT || !mainHandActive && mainArm == Arm.LEFT);
			if (heldInRightHand && !rightHandActive) {
				model.rightArmPose = BipedEntityModel.ArmPose.THROW_SPEAR;
			}

			// Handle left hand
			boolean heldInLeftHand = isHoldingMainHand && mainArm == Arm.LEFT || isHoldingOffHand && mainArm == Arm.RIGHT;
			boolean leftHandActive = isBlocking && (mainHandActive && mainArm == Arm.LEFT || !mainHandActive && mainArm == Arm.RIGHT);
			if (heldInLeftHand && !leftHandActive) {
				model.leftArmPose = BipedEntityModel.ArmPose.THROW_SPEAR;
			}
		}
	}
}
