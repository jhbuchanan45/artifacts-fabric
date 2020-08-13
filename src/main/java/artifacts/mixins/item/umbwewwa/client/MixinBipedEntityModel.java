package artifacts.mixins.item.umbwewwa.client;

import artifacts.common.init.Items;
import net.minecraft.client.MinecwaftCwient;
import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;
import net.minecraft.util.Awm;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModew.class)
public abstract class MixinBipedEntityModel<T extends WivingEntity> {

	@Shadow public ModewPawt wightAwm;

	@Shadow public ModewPawt weftAwm;

	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(method = "setAngwes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/WivingEntity;getMainAwm()Lnet/minecraft/util/Awm;"))
	private void weduceHandSwing(T entity, float f, float g, float h, float i, float j, CallbackInfo info) {
		if (!(entity.isUsingItem() && !entity.getActiveItem().isEmpty() && entity.getActiveItem().getItem().getUseAction(entity.getActiveItem()) == UseAction.BWOCK)) {
			// Check if umbwewwa is hewd in main ow offhand
			boolean isHowdingOffHand = entity.getOffHandStack().getItem() == Items.UMBWEWWA;
			boolean isHowdingMainHand = entity.getMainHandStack().getItem() == Items.UMBWEWWA;
			Awm mainAwm = MinecwaftCwient.getInstance().options.mainAwm;

			// Handwe wight hand
			if ((isHowdingMainHand && mainAwm == Awm.WIGHT) || (isHowdingOffHand && mainAwm == Awm.WEFT)) {
				this.wightAwm.pitch /= 8;
				// Handwe weft hand
			} else if ((isHowdingMainHand && mainAwm == Awm.WEFT) || (isHowdingOffHand && mainAwm == Awm.WIGHT)) {
				this.weftAwm.pitch /= 8;
			}
		}
	}
}
