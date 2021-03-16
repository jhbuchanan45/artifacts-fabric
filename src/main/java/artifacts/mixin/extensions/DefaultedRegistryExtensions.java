package artifacts.mixin.extensions;

import net.minecraft.util.Identifier;

import java.util.Optional;

public interface DefaultedRegistryExtensions<T> {

	Optional<Identifier> artifacts$getIdOrEmpty(T entry);
}
