package artifacts.mixin.mixins.item.pendant;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "onStruckByLightning", at = @At("HEAD"), cancellable = true)
	private void lightningImmune(ServerWorld world, LightningEntity lightning, CallbackInfo info) {
		//noinspection ConstantConditions
		if ((Entity) (Object) this instanceof LivingEntity) {
			if (TrinketsHelper.isEquipped(Items.SHOCK_PENDANT, (LivingEntity) (Object) this)) {
				info.cancel();
			}
		}
	}
}
