package artifacts.mixin.mixins.accessors;

import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DamageSource.class)
public interface DamageSourceAccessor {

	// TODO: Temproray fix for https://github.com/SpongePowered/Mixin/issues/430
	@Invoker("setFire")
	DamageSource artifacts$callSetFire();
}
