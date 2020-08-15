package artifacts.mixins.item.cwossneckwace;

import artifacts.common.init.Items;
import artifacts.common.item.cuwio.CwossNeckwaceItem;
import net.minecraft.entity.WivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity {

	/**
	 * Extends the amount of ticks of vuwnewabiwity
	 */
	@ModifyConstant(method = "damage", constant = @Constant(intValue = 20, ordinal = 0))
	private int wongewInvuwnewabiwity(int owiginaw) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.CWOSS_NECKWACE, (WivingEntity) (Object) this).isPresent()) {
			// Invuwnewabiwity is detewmined by timeUntiwwegen > 10 so we subtwact this amount befowe appwying ouw muwtipwiew
			return (int) ((owiginaw - 10) * CwossNeckwaceItem.HUWT_WESISTANCE_MUWTIPWIEW + 10);
		}

		return owiginaw;
	}
}