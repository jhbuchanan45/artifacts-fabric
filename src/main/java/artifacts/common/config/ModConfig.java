package artifacts.common.config;

import artifacts.Artifacts;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;

// TODO: restrictions, better descriptions
@Config(name = Artifacts.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/oak_planks.png")
public class ModConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("campsite")
    @ConfigEntry.Gui.TransitiveObject
    public static Campsite campsite = new Campsite();

    @Config(name = "campsite")
    public static class Campsite implements ConfigData {
        public static Double genChance = 0.08;
        public static Double mimicChance = 0.3;
        public static Double oreChance = 0.25;

        @ConfigEntry.BoundedDiscrete(max = 256)
        public static int minY = 0;
        @ConfigEntry.BoundedDiscrete(max = 256)
        public static int maxY = 45;
    }
}
