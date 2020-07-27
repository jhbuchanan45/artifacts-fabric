package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.item.LuckyScarfItem;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.IForgeRegistry;

public class LootModifiers {

    public static void register(IForgeRegistry<GlobalLootModifierSerializer<?>> registry) {
        registry.registerAll(
                new LuckyScarfItem.FortuneBonusModifier.Serializer().setRegistryName(new Identifier(Artifacts.MOD_ID, "fortune_bonus"))
        );
    }
}
