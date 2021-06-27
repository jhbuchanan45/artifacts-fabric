package artifacts.mixin.mixins.item.heliumflamingo.client;

import artifacts.Artifacts;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

	@Shadow @Final private MinecraftClient client;
	@Unique private final Identifier FLAMINGO_ICONS_TEXTURE = Artifacts.id("textures/gui/icons.png");

	@Redirect(method = "renderStatusBars", allow = 2, expect = 2, require = 0,
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
			slice = @Slice(from = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = {"ldc=air"})))
	private void renderFlamingoAir(InGameHud inGameHud, MatrixStack matrices, int x, int y, int u, int v, int width, int height) {
		matrices.push();
		this.client.getTextureManager().bindTexture(FLAMINGO_ICONS_TEXTURE);
		DrawableHelper.drawTexture(matrices, x, y, u - 16, v - 18, width, height, 32, 16);
		matrices.pop();
	}
}
