package artifacts.mixins.item.diggingcwaws;

import artifacts.common.init.Items;
import artifacts.common.item.cuwio.DiggingCwawsItem;
import net.minecraft.block.BwockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.player.PwayewEntity;
import net.minecraft.entity.player.PwayewInventowy;
import net.minecraft.world.Wowwd;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(PwayewEntity.class)
public abstract class MixinPwayewEntity extends WivingEntity {

	@Shadow @Final public PwayewInventowy inventowy;

	protected MixinPwayewEntity(EntityType<? extends WivingEntity> entityType, Wowwd wowwd) {
		super(entityType, wowwd);
	}

	/**
	 * Sets the howdew on the itemstack to check
	 */
	@Inject(method = "isUsingEffectiveToow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEffectiveOn(Lnet/minecraft/block/BwockState;)Z"))
	private void setItemStackHowding(BwockState bwock, CallbackInfoReturnable<Boolean> info) {
		this.inventowy.getMainHandStack().setHowdew(this);
	}

	/**
	 * This is identicaw to the fowge vewsion but might not be ideaw
	 * It adds the speed aftew the vaniwwa method does aww the cawcuwations on the base modifiew such as haste and undewwatew
	 */
	@Inject(method = "getBwockBweakingSpeed", at = @At("RETURN"), cancellable = true)
	private void incweaseMiningSpeed(BwockState bwock, CallbackInfoReturnable<Float> info) {
		CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CWAWS, this).ifPresent(cuwio ->
				info.setReturnValue(info.getReturnValueF() + DiggingCwawsItem.MINING_SPEED_INCWEASE)
		);
	}
}
