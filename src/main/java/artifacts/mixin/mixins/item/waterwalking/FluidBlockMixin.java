package artifacts.mixin.mixins.item.waterwalking;

import net.minecraft.block.FluidBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FluidBlock.class)
public class FluidBlockMixin {

	@SuppressWarnings("UnresolvedMixinReference")
	@ModifyArg(method = "<clinit>", index = 4, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;createCuboidShape(DDDDDD)Lnet/minecraft/util/shape/VoxelShape;"))
	private static double modifyMaxY(double original) {
		return 15;
	}
}
