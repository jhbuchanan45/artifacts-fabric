package artifacts.mixin.mixins.item.heliumflamingo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

/*	@Inject(method = "baseTick", at = @At(value = "INVOKE", ordinal = 0, shift = At.Shift.AFTER, target = "Lnet/minecraft/entity/LivingEntity;setAir(I)V"))
	private void airSwimTick(CallbackInfo info) {
		Components.ARTIFACT_ABILITIES.maybeGet(this).ifPresent(comp -> {
			if (this.getAir() == 0 && comp.isAirSwimming()) {
				comp.stopAirSwimming();
			}
		});
	}

	@Inject(method = "fall", at = @At("HEAD"))
	private void stopAirSwimmingOnGround(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition, CallbackInfo info) {
		if (onGround && HeliumFlamingoItem.isFlying((LivingEntity) (Object) this)) {
			Components.ARTIFACT_ABILITIES.get(this).stopAirSwimming();
		}
	}*/
}
