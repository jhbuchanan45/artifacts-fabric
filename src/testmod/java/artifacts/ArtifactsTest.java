package artifacts;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ArtifactsTest implements ModInitializer {

	private static final String MOD_ID = "artifacts-testmod";
	public static final Block LVL2_BLOCK = new Block(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.WOOD).burnable().build()).requiresTool().breakByTool(FabricToolTags.AXES, 2).strength(3));
	public static final Block LVL3_BLOCK = new Block(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.WOOD).burnable().build()).requiresTool().breakByTool(FabricToolTags.AXES, 3).strength(3));

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "lvl2_block"), LVL2_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "lvl3_block"), LVL3_BLOCK);

		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lvl2_block"), new BlockItem(LVL2_BLOCK, new Item.Settings().group(Artifacts.ITEM_GROUP)));
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lvl3_block"), new BlockItem(LVL3_BLOCK, new Item.Settings().group(Artifacts.ITEM_GROUP)));
	}
}
