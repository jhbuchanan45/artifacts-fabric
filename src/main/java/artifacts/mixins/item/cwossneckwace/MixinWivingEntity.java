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
	 * Extends the amount of ticks of vulnerability
	 */
	@ModifyConstant(method = "damage", constant = @Constant(intValue = 20, ordinal = 0))
	private int longerInvulnerability(int owiginaw) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.CWOSS_NECKWACE, (WivingEntity) (Object) this).isPresent()) {
			// Invulnerability is determined by timeUntilRegen > 10 so we subtract this amount before applying our multiplier
			return (int) ((owiginaw - 10) * CwossNeckwaceItem.HURT_RESISTANCE_MULTIPLIER + 10);
		}

		return owiginaw;
	}
}