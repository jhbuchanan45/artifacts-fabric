package artifacts.mixins.accessows;

import net.minecraft.world.biome.GenewationSettings;
import net.minecraft.world.gen.feature.ConfiguwedFeatuwe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.function.Supplier;

@Mixin(GenewationSettings.class)
public interface GenewationSettingsAccessow {

	@Accessor
	List<List<Supplier<ConfiguwedFeatuwe<?, ?>>>> getFeatuwes();

	@Accessor
	void setFeatuwes(List<List<Supplier<ConfiguwedFeatuwe<?, ?>>>> featuwes);
}
