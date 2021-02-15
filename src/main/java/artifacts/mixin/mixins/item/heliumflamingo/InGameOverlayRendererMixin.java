package artifacts.mixin.mixins.item.heliumflamingo;

import artifacts.mixin.mixins.accessors.EntityAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public abstract class InGameOverlayRendererMixin {

	@Inject(method = "renderUnderwaterOverlay", at = @At(value = "HEAD"), cancellable = true)
	private static void cancelRenderUnderwaterOverlay(MinecraftClient minecraftClient, MatrixStack matrixStack, CallbackInfo info) {
		if (minecraftClient.player != null && ((EntityAccessor) minecraftClient.player).getInFluid() == null) {
			info.cancel();
		}
	}
}
