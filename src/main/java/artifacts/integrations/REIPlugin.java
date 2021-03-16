package artifacts.integrations;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.item.ArtifactItem;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import me.shedaniel.rei.plugin.information.DefaultInformationDisplay;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class REIPlugin implements REIPluginV0 {
	@Override
	public Identifier getPluginIdentifier() {
		return new Identifier(Artifacts.MODID, "rei_plugin");
	}

	@Override
	public void registerRecipeDisplays(RecipeHelper recipeHelper) {
		Registry.ITEM.stream()
				.filter(item -> item instanceof ArtifactItem)
				.filter(item -> item != Items.NOVELTY_DRINKING_HAT)
				.map(item -> {
					DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStack.create(new ItemStack(item)), item.getName());
					info.line(((ArtifactItem) item).getREITooltip());
					return info;
				}).forEach(recipeHelper::registerDisplay);

		// Novelty Drinking Hat
		DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStack.create(new ItemStack(Items.NOVELTY_DRINKING_HAT)), Items.NOVELTY_DRINKING_HAT.getName());
		info.line(((ArtifactItem) Items.PLASTIC_DRINKING_HAT).getREITooltip());
		recipeHelper.registerDisplay(info);
	}
}
