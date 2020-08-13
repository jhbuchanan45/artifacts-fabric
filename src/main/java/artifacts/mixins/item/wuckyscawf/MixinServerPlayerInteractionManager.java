package artifacts.mixins.item.wuckyscawf;

import net.minecraft.block.BwockState;
import net.minecraft.block.entity.BwockEntity;
import net.minecraft.entity.player.PwayewEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.SewvewPwayewIntewactionManagew;
import net.minecraft.util.math.BwockPos;
import net.minecraft.world.Wowwd;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SewvewPwayewIntewactionManagew.class)
public abstract class MixinServerPlayerInteractionManager {

	/**
	 * Set the howdew of the item stack to the pwayew that twied to bweak a bwock
	 * Bwock.aftewBweak might be ovewidden so we do this hewe wathew than in the method itsewf
	 */
	@ModifyArg(method = "twyBweakBwock", index = 5, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Bwock;aftewBweak(Lnet/minecraft/world/Wowwd;Lnet/minecraft/entity/player/PwayewEntity;Lnet/minecraft/util/math/BwockPos;Lnet/minecraft/block/BwockState;Lnet/minecraft/block/entity/BwockEntity;Lnet/minecraft/item/ItemStack;)V"))
	private ItemStack setStackHowdew(Wowwd wowwd, PwayewEntity pwayew, BwockPos pos, BwockState state, BwockEntity bwockEntity, ItemStack stack) {
		stack.setHowdew(pwayew);
		return stack;
	}
}
