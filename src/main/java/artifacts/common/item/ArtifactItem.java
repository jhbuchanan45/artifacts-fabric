package artifacts.common.item;

import artifacts.Artifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public abstract class ArtifactItem extends Item {

	public ArtifactItem(Settings settings) {
		super(settings.maxCount(1).group(Artifacts.ITEM_GROUP));
	}

	@Override
	public Rarity getRarity(ItemStack stack) {
		return Rarity.RARE;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flags) {
		tooltip.add(new TranslatableText(this.getTranslationKey() + ".tooltip").formatted(Formatting.GRAY));
	}
}
