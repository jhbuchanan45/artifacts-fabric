package artifacts.integrations;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.item.ArtifactItem;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

public class REIPlugin implements REIClientPlugin {
	@Override
	public void registerDisplays(DisplayRegistry registry) {
		Registry.ITEM.stream()
				.filter(item -> item instanceof ArtifactItem)
				.filter(item -> item != Items.NOVELTY_DRINKING_HAT)
				.map(item -> {
					DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(item)), item.getName());
					info.line(((ArtifactItem) item).getREITooltip());
					return info;
				}).forEach(registry::add);

		// Novelty Drinking Hat
		DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.NOVELTY_DRINKING_HAT)), Items.NOVELTY_DRINKING_HAT.getName());
		info.line(((ArtifactItem) Items.PLASTIC_DRINKING_HAT).getREITooltip());
		registry.add(info);
	}

}
