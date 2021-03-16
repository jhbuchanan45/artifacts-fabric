package artifacts.mixin.mixins.util;

import artifacts.mixin.extensions.DefaultedRegistryExtensions;
import com.mojang.serialization.Lifecycle;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

@Mixin(DefaultedRegistry.class)
public abstract class DefaultedRegistryMixin<T> extends SimpleRegistry<T> implements DefaultedRegistryExtensions<T> {

	public DefaultedRegistryMixin(RegistryKey<? extends Registry<T>> registryKey, Lifecycle lifecycle) {
		super(registryKey, lifecycle);
	}

	@Override
	@Unique
	public Optional<Identifier> artifacts$getIdOrEmpty(T entry) {
		return Optional.ofNullable(super.getId(entry));
	}
}
