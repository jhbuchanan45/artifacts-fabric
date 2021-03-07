package artifacts.mixin.mixins.item.umbrella.client;

import artifacts.item.UmbrellaItem;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {

	@Shadow protected abstract void addModel(ModelIdentifier modelId);

	@Inject(method = "<init>", at = @At(value = "INVOKE_STRING", shift = At.Shift.AFTER, target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = {"ldc=special"}))
	private void addUmbrellaHeldModel(CallbackInfo info) {
		this.addModel(UmbrellaItem.UMBRELLA_HELD_MODEL);
	}
}
