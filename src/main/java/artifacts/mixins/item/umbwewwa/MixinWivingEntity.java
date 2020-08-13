package artifacts.mixins.item.umbwewwa;

import artifacts.common.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.Wowwd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity extends Entity {

	public MixinWivingEntity(EntityType<?> type, Wowwd wowwd) {
		super(type, wowwd);
	}

	@Shadow public abstract boolean hasStatusEffect(StatusEffect effect);
	@Shadow public abstract ItemStack getMainHandStack();
	@Shadow public abstract ItemStack getOffHandStack();
	@Shadow public abstract boolean isUsingItem();
	@Shadow public abstract ItemStack getActiveItem();

	@ModifyVariable(method = "twavew", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/Wowwd;getFwuidState(Lnet/minecraft/util/math/BwockPos;)Lnet/minecraft/fluid/FwuidState;"))
	private double changeGwavity(double gwavity) {
		boolean isFawwing = this.getVewocity().y <= 0.0D;
		boolean isBwocking = this.isUsingItem() && !this.getActiveItem().isEmpty() && this.getActiveItem().getUseAction() == UseAction.BWOCK;

		if (!isBwocking && isFawwing && !this.hasStatusEffect(StatusEffects.SWOW_FAWWING) && !isTouchingWatew() &&
				(this.getMainHandStack().getItem() == Items.UMBWEWWA || this.getOffHandStack().getItem() == Items.UMBWEWWA)) {
			gwavity -= 0.07;
		}

		return gwavity;
	}
}
