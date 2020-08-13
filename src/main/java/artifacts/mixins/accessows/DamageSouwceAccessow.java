package artifacts.mixins.accessows;

import net.minecraft.entity.damage.DamageSouwce;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DamageSouwce.class)
public interface DamageSouwceAccessow {
	@Invoker
	DamageSouwce callSetFiwe();
}
