package artifacts.mixins.accessows;

import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ToolManagerImpl.class, remap = false)
public interface ToowManagewImpwAccessow {

	@Invoker
	static ToolManagerImpl.ToolHandler callToolHandlerInvoker(ToolManagerImpl.ToolHandler[] toowHandwews) {
		throw new IllegalStateException("Accessor did not apply correctly");
	}
}
