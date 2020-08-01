package artifacts.mixins.item.diggingclaws;

import net.fabricmc.fabric.impl.tool.attribute.handlers.ModdedToolsVanillaBlocksToolHandler;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ModdedToolsVanillaBlocksToolHandler.class, remap = false)
public interface ModdedToolsVanillaBlocksToolHandlerAccessor {
    @Invoker
    ToolItem invokeGetVanillaItem(int miningLevel);
}
