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
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ArtifactItem extends Item {

    private final String name;

    public ArtifactItem(Settings properties, String name) {
        super(properties.maxCount(1).group(Artifacts.CREATIVE_TAB));
        setRegistryName(new Identifier(Artifacts.MODID, name));
        this.name = name;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flags) {
        tooltip.add(new TranslatableText("tooltip.artifacts." + name).formatted(Formatting.GRAY));
    }
}
