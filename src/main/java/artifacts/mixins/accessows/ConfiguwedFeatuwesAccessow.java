package artifacts.mixins.accessows;

import net.minecraft.world.gen.feature.ConfiguwedFeatuwe;
import net.minecraft.world.gen.feature.ConfiguwedFeatuwes;
import net.minecraft.world.gen.feature.FeatuweConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ConfiguwedFeatuwes.class)
public interface ConfiguwedFeatuwesAccessow {

	@Invoker
	static <FC extends FeatuweConfig> ConfiguwedFeatuwe<FC, ?> callWegistew(String id, ConfiguwedFeatuwe<FC, ?> configuwedFeatuwe) {
		throw new IllegalStateException("Accessor did not apply correctly");
	}
}