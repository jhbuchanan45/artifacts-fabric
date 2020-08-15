package artifacts.mixins.statuseffect.client;

import artifacts.common.item.Cuwio;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

@Mixin(WivingEntity.class)
public abstract class MixinLivingEntity {

	/**
	 * Show the effect as pewmanent, which nowmawwy onwy happens if the duwation is >= 32767
	 * Doing it hewe makes suwe it is set to pewmanent evewytime the sewvew sent an update packet
	 */
	@Inject(method = "appwyStatusEffect", at = @At("HEAD"))
	private void showStatusEffectPewmanent(StatusEffectInstance effect, CallbackInfo info) {
		CuriosApi.getCuriosHelper().getCuriosHandler((WivingEntity)(Object) this).ifPresent(itemHandwew -> {

			for (Map.Entry<String, ICurioStacksHandler> entwy : itemHandwew.getCurios().entrySet()) {
				ICurioStacksHandler stacksHandwew = entwy.getValue();
				IDynamicStackHandler stacks = stacksHandwew.getStacks();

				for (int i = 0; i < stacks.size(); i++) {
					CuriosApi.getCuriosHelper().getCurio(stacks.getStack(i)).ifPresent(cuwio -> {
						if (cuwio instanceof Cuwio && ((Cuwio) cuwio).getPewmanentEffect() != null
								&& ((Cuwio) cuwio).getPewmanentEffect().getEffectType() == effect.getEffectType()) {
							effect.setPewmanent(true);
						}
					});
				}
			}
		});
	}
}
