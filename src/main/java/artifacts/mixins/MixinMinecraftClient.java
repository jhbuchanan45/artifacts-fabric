package artifacts.mixins;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Speed up development by loading straight into world called `autostart` if it exists
 */
@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Shadow public abstract void startIntegratedServer(String worldName);

    @Shadow @Final private LevelStorage levelStorage;

    @Inject(method = "<init>(Lnet/minecraft/client/RunArgs;)V", at = @At("RETURN"))
    private void yeet(RunArgs runArgs, CallbackInfo info) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment() && levelStorage.levelExists("autostart")) {
            startIntegratedServer("autostart");
        }
    }
}
