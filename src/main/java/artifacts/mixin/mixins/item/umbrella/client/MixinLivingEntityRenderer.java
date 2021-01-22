package artifacts.mixin.mixins.item.umbrella.client;

import artifacts.common.item.UmbrellaItem;
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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class MixinLivingEntityRenderer<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> {

	@Shadow
	protected M model;

	protected MixinLivingEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Inject(method = "render", at = @At("HEAD"))
	private void renderUmbrella(T entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo info) {
		if (this.model instanceof BipedEntityModel) {
			//noinspection rawtypes
			BipedEntityModel model = (BipedEntityModel) this.model;

			boolean heldMainHand = UmbrellaItem.getHeldStatusForHand(entity, Hand.MAIN_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
			boolean heldOffHand = UmbrellaItem.getHeldStatusForHand(entity, Hand.OFF_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
			boolean rightHanded = MinecraftClient.getInstance().options.mainArm == Arm.RIGHT;

			if ((heldMainHand && rightHanded) || (heldOffHand && !rightHanded)) {
				model.rightArmPose = BipedEntityModel.ArmPose.THROW_SPEAR;
			}

			if ((heldMainHand && !rightHanded) || (heldOffHand && rightHanded)) {
				model.leftArmPose = BipedEntityModel.ArmPose.THROW_SPEAR;
			}
		}
	}
}
