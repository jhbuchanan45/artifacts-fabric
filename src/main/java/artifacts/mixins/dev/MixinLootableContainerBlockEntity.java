package artifacts.mixins.dev;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LootableContainerBlockEntity.class)
public class MixinLootableContainerBlockEntity {

	/**
	 * Allow spectators to generate loot in development environments
	 */
	@Redirect(method = "checkUnlocked", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isSpectator()Z"))
	private boolean spectatorUnlockable(PlayerEntity player) {
		return !FabricLoader.getInstance().isDevelopmentEnvironment() && player.isSpectator();
	}
}
