package artifacts.common.config;

import artifacts.Awtifacts;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;

// TODO: restrictions, better descriptions
@Config(name = Awtifacts.MODID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class ModConfig extends PartitioningSerializer.GlobalData {

	@ConfigEntry.Category("items")
	@ConfigEntry.Gui.TransitiveObject
	public Items items = new Items();

	@ConfigEntry.Category("campsite")
	@ConfigEntry.Gui.TransitiveObject
	public Campsite campsite = new Campsite();

	@Config(name = "items")
	public static class Items implements ConfigData {
		public int everlastingFoodCooldown = 300;
	}

	@Config(name = "campsite")
	public static class Campsite implements ConfigData {
		public double genChance = 0.08;
		public double mimicChance = 0.3;
		public double oweChance = 0.25;

		@ConfigEntry.BoundedDiscrete(max = 256)
		public int minY = 0;
		@ConfigEntry.BoundedDiscrete(max = 256)
		public int maxY = 45;
	}
}
