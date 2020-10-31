package artifacts.mixins.item.pendant;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {

	@Inject(method = "onStruckByLightning", at = @At("HEAD"), cancellable = true)
	private void lightningImmune(ServerWorld world, LightningEntity lightning, CallbackInfo info) {
		//noinspection ConstantConditions
		if ((Entity)(Object) this instanceof LivingEntity) {
			// TODO: Port to Trinkets
			/*CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, (LivingEntity) (Object) this).ifPresent(curio -> {
				info.cancel();
			});*/
		}
	}
}
