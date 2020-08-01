package artifacts.common.toolhandler;

import artifacts.common.init.Items;
import net.fabricmc.fabric.api.tool.attribute.v1.DynamicAttributeTool;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
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
 * For mining vanilla/modded blocks with non-tools
 */
public class DiggingClawsNonToolsToolHandler implements ToolManagerImpl.ToolHandler {

    private final List<Item> vanillaItems;

    public DiggingClawsNonToolsToolHandler(List<Item> vanillaItems) {
        this.vanillaItems = vanillaItems;
    }

    @Override
    public ActionResult isEffectiveOn(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user) {
        if (!(stack.getItem() instanceof DynamicAttributeTool || stack.getItem() instanceof ToolItem)
                && CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CLAWS, user).isPresent()) {

            ToolManagerImpl.Entry entry = ToolManagerImpl.entryNullable(state.getBlock());
            if (entry != null) { // Modded block
                int requiredMiningLevel = entry.getMiningLevel(tag);
                return requiredMiningLevel >= 0 && requiredMiningLevel <= 2 ? ActionResult.SUCCESS : ActionResult.PASS;
            } else { // Vanilla block
                // Success if any of the iron tools pass
                for (Item tool : vanillaItems) {
                    if (new ItemStack(tool).isEffectiveOn(state)) {
                        return ActionResult.SUCCESS;
                    }
                }
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<Float> getMiningSpeedMultiplier(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user) {
        return TypedActionResult.pass(1.0F);
    }
}
