package artifacts.common.init;

import artifacts.common.toolhandler.DiggingClawsNonToolsToolHandler;
import artifacts.common.toolhandler.DiggingClawsVanillaToolsVanillaBlocksToolHandler;
import artifacts.mixins.item.diggingclaws.ToolManagerImplAccessor;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;

import java.util.Arrays;

public class ToolHandlers {

    // Custom ToolHandler event that runs on all items (not limited to fabric api's tool tags)
    public static final Event<ToolManagerImpl.ToolHandler> NON_TOOLS_HANDLER = EventFactory.createArrayBacked(ToolManagerImpl.ToolHandler.class, ToolManagerImplAccessor::invokeToolHandlerInvoker);

    public static void register() {
        NON_TOOLS_HANDLER.register(new DiggingClawsNonToolsToolHandler(
                Arrays.asList(
                        net.minecraft.item.Items.IRON_AXE,
                        net.minecraft.item.Items.IRON_HOE,
                        net.minecraft.item.Items.IRON_PICKAXE,
                        net.minecraft.item.Items.IRON_SHOVEL,
                        net.minecraft.item.Items.IRON_SWORD,
                        net.minecraft.item.Items.SHEARS // TODO: is this the same as the forge version?
                )
        ));

        ToolManagerImpl.tag(FabricToolTags.PICKAXES).register(new DiggingClawsVanillaToolsVanillaBlocksToolHandler(
                Arrays.asList(
                        net.minecraft.item.Items.WOODEN_PICKAXE,
                        net.minecraft.item.Items.STONE_PICKAXE,
                        net.minecraft.item.Items.IRON_PICKAXE,
                        net.minecraft.item.Items.DIAMOND_PICKAXE,
                        net.minecraft.item.Items.NETHERITE_PICKAXE
                )
        ));
        ToolManagerImpl.tag(FabricToolTags.AXES).register(new DiggingClawsVanillaToolsVanillaBlocksToolHandler(
                Arrays.asList(
                        net.minecraft.item.Items.WOODEN_AXE,
                        net.minecraft.item.Items.STONE_AXE,
                        net.minecraft.item.Items.IRON_AXE,
                        net.minecraft.item.Items.DIAMOND_AXE,
                        net.minecraft.item.Items.NETHERITE_AXE
                )
        ));
        ToolManagerImpl.tag(FabricToolTags.SHOVELS).register(new DiggingClawsVanillaToolsVanillaBlocksToolHandler(
                Arrays.asList(
                        net.minecraft.item.Items.WOODEN_SHOVEL,
                        net.minecraft.item.Items.STONE_SHOVEL,
                        net.minecraft.item.Items.IRON_SHOVEL,
                        net.minecraft.item.Items.DIAMOND_SHOVEL,
                        net.minecraft.item.Items.NETHERITE_SHOVEL
                )
        ));
        ToolManagerImpl.tag(FabricToolTags.HOES).register(new DiggingClawsVanillaToolsVanillaBlocksToolHandler(
                Arrays.asList(
                        net.minecraft.item.Items.WOODEN_HOE,
                        net.minecraft.item.Items.STONE_HOE,
                        net.minecraft.item.Items.IRON_HOE,
                        net.minecraft.item.Items.DIAMOND_HOE,
                        net.minecraft.item.Items.NETHERITE_HOE
                )
        ));
        ToolManagerImpl.tag(FabricToolTags.SWORDS).register(new DiggingClawsVanillaToolsVanillaBlocksToolHandler(
                Arrays.asList(
                        net.minecraft.item.Items.WOODEN_SWORD,
                        net.minecraft.item.Items.STONE_SWORD,
                        net.minecraft.item.Items.IRON_SWORD,
                        net.minecraft.item.Items.DIAMOND_SWORD,
                        net.minecraft.item.Items.NETHERITE_SWORD
                )
        ));
        // TODO: do the diggingclaws affect shears on forge?
        //ToolManagerImpl.tag(FabricToolTags.SHEARS).register(new ArtifactsVanillaToolsVanillaBlocksToolHandler());
    }
}
