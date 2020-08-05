package artifacts.mixins.item.obsidianskull;

import artifacts.common.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(Entity.class)
public class MixinEntity {

	@Inject(method = "setOnFireFor", at = @At("HEAD"))
	private void giveFireResistance(int seconds, CallbackInfo info) {
		//noinspection ConstantConditions
		if ((Entity)(Object) this instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)(Object) this;

			if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.OBSIDIAN_SKULL, player).isPresent() && !player.getItemCooldownManager().isCoolingDown(Items.OBSIDIAN_SKULL)) {
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 0, false, true));
				player.getItemCooldownManager().set(Items.OBSIDIAN_SKULL, 1200);
			}
		}
	}
}
