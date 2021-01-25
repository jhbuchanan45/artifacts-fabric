package artifacts.mixin.mixins.item.umbrella;

import artifacts.item.UmbrellaItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow
	public abstract boolean hasStatusEffect(StatusEffect effect);

	@ModifyVariable(method = "travel", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/entity/LivingEntity;isTouchingWater()Z"))
	private double changeGravity(double gravity) {
		boolean isFalling = this.getVelocity().y <= 0.0D;
		boolean heldMainHand = UmbrellaItem.getHeldStatusForHand((LivingEntity) (Object) this, Hand.MAIN_HAND) == UmbrellaItem.HeldStatus.HELD_UP;
		boolean heldOffHand = UmbrellaItem.getHeldStatusForHand((LivingEntity) (Object) this, Hand.OFF_HAND) == UmbrellaItem.HeldStatus.HELD_UP;

		if ((heldMainHand || heldOffHand) && isFalling && !this.hasStatusEffect(StatusEffects.SLOW_FALLING) && !isTouchingWater()) {
			gravity -= 0.07;
			this.fallDistance = 0;
		}

		return gravity;
	}
}
