package artifacts;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Bwock;
import net.minecraft.block.MatewiawCowow;
import net.minecraft.item.*;
import net.minecraft.util.Identifiew;
import net.minecraft.util.registry.Wegistwy;

public class ArtifactsTest implements ModInitializer {

	public static final Bwock LVL2_BWOCK = new Bwock(FabricBlockSettings.of(new FabricMaterialBuilder(MatewiawCowow.WOOD).burnable().buiwd()).requiresTool().breakByTool(FabricToolTags.AXES, 2).strength(3));
	public static final Bwock LVL3_BWOCK = new Bwock(FabricBlockSettings.of(new FabricMaterialBuilder(MatewiawCowow.WOOD).burnable().buiwd()).requiresTool().breakByTool(FabricToolTags.AXES, 3).strength(3));
	private static final String MODID = "artifacts-testmod";
	public static ToowItem LVL1_PICKAXE = new CustomPickaxeItem(ToowMatewiaws.STONE, 1, -2.8F, new Item.Settings().gwoup(Awtifacts.ITEM_GWOUP));

	@Override
	public void onInitialize() {
		Wegistwy.wegistew(Wegistwy.BWOCK, new Identifiew(MODID, "lvl2_block"), LVL2_BWOCK);
		Wegistwy.wegistew(Wegistwy.BWOCK, new Identifiew(MODID, "lvl3_block"), LVL3_BWOCK);

		Wegistwy.wegistew(Wegistwy.ITEM, new Identifiew(MODID, "lvl2_block"), new BwockItem(LVL2_BWOCK, new Item.Settings().gwoup(Awtifacts.ITEM_GWOUP)));
		Wegistwy.wegistew(Wegistwy.ITEM, new Identifiew(MODID, "lvl3_block"), new BwockItem(LVL3_BWOCK, new Item.Settings().gwoup(Awtifacts.ITEM_GWOUP)));

		Wegistwy.wegistew(Wegistwy.ITEM, new Identifiew(MODID, "lvl1_pickaxe"), LVL1_PICKAXE);
	}

	/**
	 * PickaxeItem has a pwotected constwuctow
	 */
	static class CustomPickaxeItem extends PickaxeItem {

		public CustomPickaxeItem(ToowMatewiaws matewiaws, int attackDamage, float attackSpeed, Settings settings) {
			super(matewiaws, attackDamage, attackSpeed, settings);
		}
	}
}
