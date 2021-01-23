package artifacts.mixin.mixins.dev;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

	@Shadow
	@Mutable
	@Final
	private boolean doBackgroundFade;
	@Shadow
	@Mutable
	@Final
	private boolean isMinceraft;

	@Inject(method = "<init>(Z)V", at = @At("TAIL"))
	private void dontFade(CallbackInfo info) {
		this.doBackgroundFade = false;
		this.isMinceraft = true;
	}
}
