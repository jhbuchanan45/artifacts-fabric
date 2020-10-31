package artifacts.mixins.item.crossnecklace;

import artifacts.common.init.Items;
import artifacts.common.item.trinket.CrossNecklaceItem;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {

	/**
	 * Extends the amount of ticks of vulnerability
	 */
	@ModifyConstant(method = "damage", constant = @Constant(intValue = 20, ordinal = 0))
	private int longerInvulnerability(int original) {
		if (TrinketsHelper.isEquipped(Items.CROSS_NECKLACE, (LivingEntity)(Object) this)) {
			// Invulnerability is determined by timeUntilRegen > 10 so we subtract this amount before applying our multiplier
			return (int) ((original - 10) * CrossNecklaceItem.HURT_RESISTANCE_MULTIPLIER + 10);
		}

		return original;
	}
}