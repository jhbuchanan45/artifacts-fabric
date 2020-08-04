package artifacts.common.toolhandler;

import artifacts.common.init.Items;
import artifacts.common.item.DiggingClawsItem;
import artifacts.mixins.accessors.ToolManagerImplEntryImplAccessor;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Arrays;
import java.util.List;

/**
 * For mining vanilla/modded blocks with non-tools
 */
public class DiggingClawsToolHandler implements ToolManagerImpl.ToolHandler {

	private final List<Item> vanillaItems;

	public DiggingClawsToolHandler(List<Item> vanillaItems) {
		this.vanillaItems = vanillaItems;
	}

	@Override
	public ActionResult isEffectiveOn(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CLAWS, user).isPresent()) {

			// Modded block
			ToolManagerImpl.Entry entry = ToolManagerImpl.entryNullable(state.getBlock());
			if (entry != null) {
				// Problem: modded blocks can have different mining levels for different tools
				// Solution: get the lowest miningLevel
				int requiredMiningLevel = Arrays.stream(((ToolManagerImplEntryImplAccessor) entry).getTagLevels()).min().orElse(-1);
				return requiredMiningLevel >= 0 && requiredMiningLevel <= DiggingClawsItem.NEW_BASE_MINING_LEVEL ? ActionResult.SUCCESS : ActionResult.PASS;
			}

			// Vanilla block
			for (Item tool : vanillaItems) {
				// Success if any of the iron tools pass
				if (new ItemStack(tool).isEffectiveOn(state)) {
					return ActionResult.SUCCESS;
				}
			}
		}

		return ActionResult.PASS;
	}
}
