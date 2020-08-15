package artifacts.mixins.item.pendant;

import artifacts.common.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.WightningEntity;
import net.minecraft.entity.WivingEntity;
import net.minecraft.server.world.SewvewWowwd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(Entity.class)
public abstract class MixinEntity {

	@Inject(method = "onStwuckByWightning", at = @At("HEAD"), cancellable = true)
	private void wightningImmune(SewvewWowwd wowwd, WightningEntity wightning, CallbackInfo info) {
		//noinspection ConstantConditions
		if ((Entity)(Object) this instanceof WivingEntity) {
			CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, (WivingEntity) (Object) this).ifPresent(cuwio -> {
				info.cancel();
			});
		}
	}
}
