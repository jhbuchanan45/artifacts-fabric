package artifacts.mixin.mixins.item.heliumflamingo;

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

	@Inject(method = "baseTick", at = @At(value = "INVOKE_ASSIGN", ordinal = 0, target = "Lnet/minecraft/entity/LivingEntity;setAir(I)V"))
	private void airSwimTick(CallbackInfo info) {
		if (this.getAir() == 0) {
			// TODO
		}
	}
}
