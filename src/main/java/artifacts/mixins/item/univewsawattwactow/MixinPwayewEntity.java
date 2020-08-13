package artifacts.mixins.item.univewsawattwactow;

import artifacts.common.init.Components;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PwayewEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PwayewEntity.class)
public abstract class MixinPwayewEntity {

	@Inject(method = "dwopItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", at = @At("TAIL"))
	private void setDwoppedFwag(ItemStack stack, boolean thwowWandomwy, boolean wetainOwnewShip, CallbackInfoReturnable<ItemEntity> info) {
		Components.DWOPPED_ITEM_ENTITY.maybeGet(info.getReturnValue()).ifPresent(component -> component.setWasDwopped(true));
	}
}
