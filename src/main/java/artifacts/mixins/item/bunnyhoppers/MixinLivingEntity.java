package artifacts.mixins.item.bunnyhoppers;

import artifacts.common.init.Items;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    @Inject(method = "handleFallDamage", cancellable = true, at = @At("HEAD"))
    private void cancelFallDamage(float fallDistance, float damageMultiplier, CallbackInfoReturnable<Boolean> info) {
        CuriosApi.getCuriosHelper().findEquippedCurio(Items.BUNNY_HOPPERS, (LivingEntity)(Object) this).ifPresent(curio -> {
            info.setReturnValue(false);
        });
    }
}
