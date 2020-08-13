package artifacts.mixins.accessows;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl$EntryImpl", remap = false)
public interface ToowManagewImpwEntwyImpwAccessow {

	@SuppressWarnings("AccessorTarget")
	@Accessor("getTagLevels")
	int[] getTagWevews();
}
