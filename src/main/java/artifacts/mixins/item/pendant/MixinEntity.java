package artifacts.mixins.item.pendant;

import artifacts.common.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Inject(method = "onStruckByLightning", at = @At("HEAD"), cancellable = true)
    private void lightningImmune(LightningEntity lightning, CallbackInfo info) {
        //noinspection ConstantConditions
        if ((Entity)(Object) this instanceof LivingEntity) {
            CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, (LivingEntity)(Object) this).ifPresent(curio -> {
                info.cancel();
            });
        }
    }
}
