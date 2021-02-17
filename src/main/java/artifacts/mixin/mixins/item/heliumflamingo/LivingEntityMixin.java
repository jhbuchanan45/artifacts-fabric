package artifacts.mixin.mixins.item.heliumflamingo;

import artifacts.components.ArtifactAbilitiesComponent;
import artifacts.init.Components;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
		ArtifactAbilitiesComponent comp = Components.ARTIFACT_ABILITIES.get(this);

		if (this.getAir() == 0 && comp.isAirSwimming()) {
			comp.setAirSwimming(false);
			this.fallDistance = 0;
		}
	}
}
