package artifacts.common.toolhandler;

import artifacts.common.init.Items;
import artifacts.common.item.trinket.glove.DiggingClawsItem;
import artifacts.common.trinkets.TrinketsHelper;
import artifacts.mixin.mixins.accessors.ToolManagerImplEntryImplAccessor;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * For mining vanilla/modded blocks with non-tools
 */
@SuppressWarnings("UnstableApiUsage")
public class DiggingClawsToolHandler implements ToolManagerImpl.ToolHandler {

	// Because these are mining level 2, used to check no/vanilla tools against vanilla blocks
	// TODO: hardcoded, these are not affected by DiggingClawsItem.NEW_BASE_MINING_LEVEL
	private final List<Item> vanillaItems = Arrays.asList(
		net.minecraft.item.Items.IRON_AXE,
		net.minecraft.item.Items.IRON_HOE,
		net.minecraft.item.Items.IRON_PICKAXE,
		net.minecraft.item.Items.IRON_SHOVEL,
		net.minecraft.item.Items.IRON_SWORD,
		net.minecraft.item.Items.SHEARS
	);

	@Override
	public @NotNull ActionResult isEffectiveOn(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user) {
		if (!TrinketsHelper.isEquipped(Items.DIGGING_CLAWS, user)) {
			return ActionResult.PASS;
		}

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

		return ActionResult.PASS;
	}
}
