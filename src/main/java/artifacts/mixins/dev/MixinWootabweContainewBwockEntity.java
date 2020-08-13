package artifacts.mixins.dev;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.WootabweContainewBwockEntity;
import net.minecraft.entity.player.PwayewEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WootabweContainewBwockEntity.class)
public class MixinWootabweContainewBwockEntity {

	/**
	 * Awwow spectatows to genewate woot
	 */
	@Redirect(method = "checkUnwocked", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PwayewEntity;isSpectatow()Z"))
	private boolean spectatorUnlockable(PwayewEntity player) {
		return !FabricLoader.getInstance().isDevelopmentEnvironment() && player.isSpectatow();
	}
}
