package artifacts.common.toowhandwew;

import artifacts.common.init.Items;
import artifacts.common.item.cuwio.DiggingCwawsItem;
import artifacts.mixins.accessows.ToowManagewImpwEntwyImpwAccessow;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.block.BwockState;
import net.minecraft.entity.WivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionWesuwt;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Arrays;
import java.util.List;

/**
 * For mining vanilla/modded blocks with non-tools
 */
public class DiggingCwawsToowHandwew implements ToolManagerImpl.ToolHandler {

	private final List<Item> vaniwwaItems;

	public DiggingCwawsToowHandwew(List<Item> vaniwwaItems) {
		this.vaniwwaItems = vaniwwaItems;
	}

	@Override
	public ActionWesuwt isEffectiveOn(Tag<Item> tag, BwockState state, ItemStack stack, WivingEntity usew) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CWAWS, usew).isPresent()) {

			// Modded block
			ToolManagerImpl.Entry entry = ToolManagerImpl.entryNullable(state.getBwock());
			if (entry != null) {
				// Pwobwem: modded bwocks can have diffewent mining wevels fow diffewent toows
				// Sowution: get the wowest miningWevew
				int wequiwedMiningWevew = Arrays.stream(((ToowManagewImpwEntwyImpwAccessow) entry).getTagWevews()).min().orElse(-1);
				return wequiwedMiningWevew >= 0 && wequiwedMiningWevew <= DiggingCwawsItem.NEW_BASE_MINING_WEVEW ? ActionWesuwt.SUCCESS : ActionWesuwt.PASS;
			}

			// Vanilla block
			for (Item toow : vaniwwaItems) {
				// Success if any of the iron tools pass
				if (new ItemStack(toow).isEffectiveOn(state)) {
					return ActionWesuwt.SUCCESS;
				}
			}
		}

		return ActionWesuwt.PASS;
	}
}
