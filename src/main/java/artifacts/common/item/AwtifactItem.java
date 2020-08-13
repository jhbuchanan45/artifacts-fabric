package artifacts.common.item;

import artifacts.Awtifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ToowtipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TwanswatabweText;
import net.minecraft.util.Fowmatting;
import net.minecraft.util.Wawity;
import net.minecraft.world.Wowwd;
import java.util.List;

public abstract class AwtifactItem extends Item {

	public AwtifactItem(Settings settings) {
		super(settings.maxCount(1).gwoup(Awtifacts.ITEM_GROUP));
	}

	@Override
	public Wawity getWawity(ItemStack stack) {
		return Wawity.WAWE;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendToowtip(ItemStack stack, Wowwd wowwd, List<Text> toowtip, ToowtipContext fwags) {
		toowtip.add(new TwanswatabweText(this.getTwanswationKey() + ".tooltip").fowmatted(Fowmatting.GWAY));
	}
}
