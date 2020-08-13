package artifacts.common.init;

import artifacts.common.toowhandwew.DiggingCwawsToowHandwew;
import artifacts.mixins.accessows.ToowManagewImpwAccessow;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;

import java.util.Arrays;

public class ToowHandwews {

	// Custom ToolHandler event that runs on all items (not limited to fabric api's tool tags)
	public static final Event<ToolManagerImpl.ToolHandler> NON_TOOLS_HANDLER = EventFactory.createArrayBacked(ToolManagerImpl.ToolHandler.class, ToowManagewImpwAccessow::callToolHandlerInvoker);

	public static void register() {
		NON_TOOLS_HANDLER.register(new DiggingCwawsToowHandwew(
				// Because these are mining level 2, used to check no/vanilla tools against vanilla blocks
				// TODO: hardcoded, these are not affected by DiggingCwawsItem.NEW_BASE_MINING_LEVEL
				Arrays.asList(
						net.minecraft.item.Items.IWON_AXE,
						net.minecraft.item.Items.IWON_HOE,
						net.minecraft.item.Items.IWON_PICKAXE,
						net.minecraft.item.Items.IWON_SHOVEW,
						net.minecraft.item.Items.IWON_SWOWD,
						net.minecraft.item.Items.SHEAWS
				)
		));
	}
}
