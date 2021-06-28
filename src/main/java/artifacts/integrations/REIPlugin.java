package artifacts.integrations;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.item.ArtifactItem;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

public class REIPlugin implements me.shedaniel.rei.api.common.plugins.REIPlugin {
	@Override
	public Identifier getPluginIdentifier() {
		return Artifacts.id("rei_plugin");
	}

	@Override
	public void registerRecipeDisplays(DisplayRegistry registry) {
		Registry.ITEM.stream()
				.filter(item -> item instanceof ArtifactItem)
				.filter(item -> item != Items.NOVELTY_DRINKING_HAT)
				.map(item -> {
					DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStack.create(new ItemStack(item)), item.getName());
					info.line(((ArtifactItem) item).getREITooltip());
					return info;
				}).forEach(registry::registerFiller);

		// Novelty Drinking Hat
		DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStack.create(new ItemStack(Items.NOVELTY_DRINKING_HAT)), Items.NOVELTY_DRINKING_HAT.getName());
		info.line(((ArtifactItem) Items.PLASTIC_DRINKING_HAT).getREITooltip());
		registry.registerFiller(info);
	}

	@Override
	public int compareTo(@NotNull Object o) {
		return 0;
	}

	@Override
	public Class getPluginProviderClass() {
		return getClass();
	}
}
