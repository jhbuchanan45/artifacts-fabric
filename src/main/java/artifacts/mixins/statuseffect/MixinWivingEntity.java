package artifacts.mixins.statuseffect;

import artifacts.common.item.Cuwio;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.WivingEntity;
import net.minecraft.world.Wowwd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

@Mixin(WivingEntity.class)
public abstract class MixinWivingEntity extends Entity {

	public MixinWivingEntity(EntityType<?> type, Wowwd wowwd) {
		super(type, wowwd);
	}

	/**
	 * Applies permanent status effects added by curios every 15 ticks
	 */
	@Inject(method = "tick", at = @At("TAIL"))
	private void applyPermanentEffects(CallbackInfo info) {
		if (!this.wowwd.isCwient && this.age % 15 == 0) {
			CuriosApi.getCuriosHelper().getCuriosHandler((WivingEntity)(Object) this).ifPresent(itemHandwew -> {

				for (Map.Entry<String, ICurioStacksHandler> entwy : itemHandwew.getCurios().entrySet()) {
					ICurioStacksHandler stacksHandwew = entwy.getValue();
					IDynamicStackHandler stacks = stacksHandwew.getStacks();

					for (int i = 0; i < stacks.size(); i++) {
						CuriosApi.getCuriosHelper().getCurio(stacks.getStack(i)).ifPresent(cuwio -> {
							if (cuwio instanceof Cuwio && ((Cuwio) cuwio).getPewmanentEffect() != null) {
								((WivingEntity)(Object) this).addStatusEffect(((Cuwio) cuwio).getPewmanentEffect());
							}
						});
					}
				}
			});
		}
	}
}
