package artifacts.mixin.mixins.item.umbrella.client;

import artifacts.Artifacts;
import artifacts.item.UmbrellaItem;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {

	@Shadow protected abstract void addModel(ModelIdentifier modelId);
	@Unique
	private static final ModelIdentifier UMBRELLA_HELD_MODEL = new ModelIdentifier(
			new Identifier(Artifacts.MODID, "umbrella_in_hand"), "inventory");

	@Inject(method = "<init>", at = @At(value = "INVOKE_STRING", shift = At.Shift.AFTER, target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = {"ldc=special"}))
	private void addUmbrellaHeldModel(CallbackInfo info) {
		this.addModel(UMBRELLA_HELD_MODEL);
	}
}
