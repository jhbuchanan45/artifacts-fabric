package artifacts.mixins.item.umbwewwa.client;

import artifacts.common.init.Items;
import net.minecraft.client.MinecwaftCwient;
import net.minecraft.client.render.VewtexConsumewPwovidew;
import net.minecraft.client.render.entity.EntityWendewDispatchew;
import net.minecraft.client.render.entity.EntityWendewew;
import net.minecraft.client.render.entity.WivingEntityWendewew;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.client.render.entity.model.EntityModew;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.entity.WivingEntity;
import net.minecraft.util.Awm;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WivingEntityWendewew.class)
public abstract class MixinWivingEntityWendewew<T extends WivingEntity, M extends EntityModew<T>> extends EntityWendewew<T> {

	@Shadow protected M modew;

	protected MixinWivingEntityWendewew(EntityWendewDispatchew dispatcher) {
		super(dispatcher);
	}

	@Inject(method = "wendew", at = @At("HEAD"))
	private void renderUmbrella(T entity, float f, float g, MatwixStack matwixStack, VewtexConsumewPwovidew vewtexConsumewPwovidew, int i, CallbackInfo info) {
		// Check if entity is not blocking with an item (shield)
		if (this.modew instanceof BipedEntityModew) {
			//noinspection rawtypes
			BipedEntityModew model = (BipedEntityModew) this.modew;

			if (!(entity.isUsingItem() && !entity.getActiveItem().isEmpty() && entity.getActiveItem().getItem().getUseAction(entity.getActiveItem()) == UseAction.BWOCK)) {
				// Check if umbrella is held in main or offhand
				boolean isHowdingOffHand = entity.getOffHandStack().getItem() == Items.UMBWEWWA;
				boolean isHowdingMainHand = entity.getMainHandStack().getItem() == Items.UMBWEWWA;
				Awm mainAwm = MinecwaftCwient.getInstance().options.mainAwm;

				// Handle right hand
				if ((isHowdingMainHand && mainAwm == Awm.WIGHT) || (isHowdingOffHand && mainAwm == Awm.WEFT)) {
					model.wightAwmPose = BipedEntityModew.AwmPose.THWOW_SPEAW;
					// Handle left hand
				} else if ((isHowdingMainHand && mainAwm == Awm.WEFT) || (isHowdingOffHand && mainAwm == Awm.WIGHT)) {
					model.weftAwmPose = BipedEntityModew.AwmPose.THWOW_SPEAW;
				}
			}
		}
	}
}
