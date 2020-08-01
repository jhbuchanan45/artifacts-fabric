package artifacts.common.init;

import artifacts.common.toolhandler.DiggingClawsToolHandler;
import artifacts.mixins.item.diggingclaws.ToolManagerImplAccessor;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;

import java.util.Arrays;

public class ToolHandlers {

	// Custom ToolHandler event that runs on all items (not limited to fabric api's tool tags)
	public static final Event<ToolManagerImpl.ToolHandler> NON_TOOLS_HANDLER = EventFactory.createArrayBacked(ToolManagerImpl.ToolHandler.class, ToolManagerImplAccessor::invokeToolHandlerInvoker);

	public static void register() {
		NON_TOOLS_HANDLER.register(new DiggingClawsToolHandler(
				// Because these are mining level 2, used to check no/vanilla tools against vanilla blocks
				Arrays.asList(
						net.minecraft.item.Items.IRON_AXE,
						net.minecraft.item.Items.IRON_HOE,
						net.minecraft.item.Items.IRON_PICKAXE,
						net.minecraft.item.Items.IRON_SHOVEL,
						net.minecraft.item.Items.IRON_SWORD
						//net.minecraft.item.Items.SHEARS // TODO: is this the same as the forge version?
				)
		));
	}
}
