package artifacts.mixin.mixins.dev.item.heliumflamingo;

import artifacts.init.Components;
import artifacts.item.trinket.HeliumFlamingoItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "baseTick", at = @At(value = "INVOKE", ordinal = 0, shift = At.Shift.AFTER, target = "Lnet/minecraft/entity/LivingEntity;setAir(I)V"))
	private void airSwimTick(CallbackInfo info) {
		/*Components.ARTIFACT_ABILITIES.maybeGet(this).ifPresent(comp -> {
			if (this.getAir() == 0 && comp.isAirSwimming()) {
				comp.stopAirSwimming();
			}
		});*/
	}

	@Inject(method = "fall", at = @At("HEAD"))
	private void stopAirSwimmingOnGround(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition, CallbackInfo info) {
  		/*if (onGround && HeliumFlamingoItem.isFlying((LivingEntity) (Object) this)) {
		    Components.ARTIFACT_ABILITIES.get(this).stopAirSwimming();
		}*/
	}
}
