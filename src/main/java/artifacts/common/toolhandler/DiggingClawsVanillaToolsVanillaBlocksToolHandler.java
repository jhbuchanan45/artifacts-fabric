package artifacts.common.toolhandler;

import artifacts.common.init.Items;
import artifacts.common.item.DiggingClawsItem;
import artifacts.mixins.item.diggingclaws.MixinModdedToolsVanillaBlocksToolHandler;
import artifacts.mixins.item.diggingclaws.ModdedToolsVanillaBlocksToolHandlerAccessor;
import net.fabricmc.fabric.api.tool.attribute.v1.DynamicAttributeTool;
import net.fabricmc.fabric.impl.tool.attribute.handlers.ModdedToolsVanillaBlocksToolHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

/**
 * For mining vanilla blocks with vanilla tools
 */
public class DiggingClawsVanillaToolsVanillaBlocksToolHandler extends ModdedToolsVanillaBlocksToolHandler {

    public DiggingClawsVanillaToolsVanillaBlocksToolHandler(List<Item> vanillaItems) {
        super(vanillaItems);
    }

    /**
     * Allow player to mine any block with mining level <= 2
     */
    // TODO: STONE HOE CAN BREAK IRON ORE
    @Override
    public ActionResult isEffectiveOn(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user) {
        if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CLAWS, user).isPresent() && !(stack.getItem() instanceof DynamicAttributeTool)) {
            // Make sure mining level is at least 2
            int miningLevel = stack.getItem() instanceof ToolItem ? Math.max(((ToolItem) stack.getItem()).getMaterial().getMiningLevel(), 2) : 2;

            // Get equivalent vanilla item for the new mining level
            ToolItem vanillaItem = ((ModdedToolsVanillaBlocksToolHandlerAccessor) this).invokeGetVanillaItem(miningLevel);

            // Would the check pass with the new vanilla tool?
            return vanillaItem.isEffectiveOn(state) ? ActionResult.SUCCESS : ActionResult.PASS;
        }
        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<Float> getMiningSpeedMultiplier(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user) {
        return TypedActionResult.pass(1.0F);
    }
}
