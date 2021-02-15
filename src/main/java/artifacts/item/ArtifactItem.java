package artifacts.item;

import artifacts.Artifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Language;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public abstract class ArtifactItem extends Item {

	public ArtifactItem(Settings settings) {
		super(settings.maxCount(1).group(Artifacts.ITEM_GROUP).rarity(Rarity.RARE));
	}

	public ArtifactItem() {
		super(new Settings());
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flags) {
		String[] lines = Language.getInstance().get(this.getTranslationKey() + ".tooltip").split("\n");

		for (String line : lines) {
			tooltip.add(new LiteralText(line).formatted(Formatting.GRAY));
		}
	}
}
