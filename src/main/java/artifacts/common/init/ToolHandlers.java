package artifacts.common.init;

import artifacts.common.toolhandler.DiggingClawsToolHandler;
import artifacts.mixins.accessors.ToolManagerImplAccessor;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;

import java.util.Arrays;

public class ToolHandlers {

	// Custom ToolHandler event that runs on all items (not limited to fabric api's tool tags)
	@SuppressWarnings("UnstableApiUsage")
	public static final Event<ToolManagerImpl.ToolHandler> NON_TOOLS_HANDLER = EventFactory.createArrayBacked(ToolManagerImpl.ToolHandler.class, ToolManagerImplAccessor::callToolHandlerInvoker);

	public static void register() {
		NON_TOOLS_HANDLER.register(new DiggingClawsToolHandler());
	}
}
