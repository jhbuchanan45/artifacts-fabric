package artifacts.mixin.mixins.item.umbrella.client;

import artifacts.common.item.UmbrellaItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> {

	@Shadow
	public ModelPart rightArm;
	@Shadow
	public ModelPart leftArm;

	// Target is unresolved because method owner is a generic T
	// Seems to work fine, but has failed to apply once or twice in dev (in a fresh runtime)
	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(method = "setAngles", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getMainArm()Lnet/minecraft/util/Arm;"))
	private void reduceHandSwing(T entity, float f, float g, float h, float i, float j, CallbackInfo info) {
		boolean heldMainHand = UmbrellaItem.getHeldStatusForHand(entity, Hand.MAIN_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
		boolean heldOffHand = UmbrellaItem.getHeldStatusForHand(entity, Hand.OFF_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
		boolean rightHanded = MinecraftClient.getInstance().options.mainArm == Arm.RIGHT;

		if ((heldMainHand && rightHanded) || (heldOffHand && !rightHanded)) {
			this.rightArm.pitch /= 8;
		}

		if ((heldMainHand && !rightHanded) || (heldOffHand && rightHanded)) {
			this.leftArm.pitch /= 8;
		}
	}
}
