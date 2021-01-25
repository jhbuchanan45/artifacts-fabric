package artifacts.mixin.mixins.statuseffect.client;

import artifacts.item.trinket.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectUtil.class)
public abstract class StatusEffectUtilMixin {

	// TODO: There might be a better place for this
	@Inject(method = "durationToString", at = @At("HEAD"), cancellable = true)
	private static void setFromArtifactString(StatusEffectInstance effect, float multiplier, CallbackInfoReturnable<String> info) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		if (player != null && effect.isPermanent()) {
			TrinketsHelper.getAllEquipped(player).forEach(stack -> {
				StatusEffectInstance trinketEffect = ((TrinketArtifactItem) stack.getItem()).getPermanentEffect();

				if (trinketEffect != null && trinketEffect.getEffectType() == effect.getEffectType()) {
					info.setReturnValue(stack.getName().getString());
				}
			});
		}
	}
}
