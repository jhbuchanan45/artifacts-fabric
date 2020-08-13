package artifacts.mixins.worldgen;

import artifacts.Artifacts;
import artifacts.common.config.ModConfig;
import artifacts.common.init.Features;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This should be termporary to get worldgen working
 */
@Mixin(Bootstrap.class)
public class MixinBootstrap {

	@Inject(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/Bootstrap;setOutputStreams()V", shift = At.Shift.AFTER), require = 1, allow = 1)
	private static void afterInitialize(CallbackInfo ci) {
		// Config
		AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(Toml4jConfigSerializer::new));
		Artifacts.CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

		Features.init();
	}
}