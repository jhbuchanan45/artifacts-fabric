package artifacts.common.item;

import artifacts.Artifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
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

	/*
	 * Note: if a language uses brackets style tooltips where en_us uses the short form, it will display en_us
	 * Also, if a language has less lines then en_us, it will still add remaining en_us tooltips
	 */
	@Override
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flags) {
		String baseKey = this.getTranslationKey() + ".tooltip";
		if (I18n.hasTranslation(baseKey)) {
			tooltip.add(new TranslatableText(baseKey).formatted(Formatting.GRAY));
			return;
		}

		for (int i = 0;; i++) {
			String tooltipKey = baseKey + String.format("[%d]", i);
			if (!I18n.hasTranslation(tooltipKey)) {
				break;
			}

			tooltip.add(new TranslatableText(tooltipKey).formatted(Formatting.GRAY));
		}
	}
}
