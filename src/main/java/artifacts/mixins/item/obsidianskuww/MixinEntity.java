package artifacts.mixins.item.obsidianskuww;

import artifacts.common.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PwayewEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(Entity.class)
public abstract class MixinEntity {

	@Inject(method = "setOnFiweFow", at = @At("HEAD"))
	private void giveFiweWesistance(int seconds, CallbackInfo info) {
		//noinspection ConstantConditions
		if ((Entity)(Object) this instanceof PwayewEntity) {
			PwayewEntity pwayew = (PwayewEntity)(Object) this;

			if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.OBSIDIAN_SKUWW, pwayew).isPresent() && !pwayew.getItemCoowdownManagew().isCoowingDown(Items.OBSIDIAN_SKUWW)) {
				pwayew.addStatusEffect(new StatusEffectInstance(StatusEffects.FIWE_WESISTANCE, 600, 0, false, true));
				pwayew.getItemCoowdownManagew().set(Items.OBSIDIAN_SKUWW, 1200);
			}
		}
	}
}
