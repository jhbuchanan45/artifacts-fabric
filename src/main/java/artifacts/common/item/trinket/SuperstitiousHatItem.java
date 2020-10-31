package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.SuperstitiousHatModel;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class SuperstitiousHatItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/superstitious_hat.png");
	private Object model;

	public SuperstitiousHatItem() {
		super(new Item.Settings());
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected SuperstitiousHatModel getModel() {
		if (model == null) {
			model = new SuperstitiousHatModel();
		}
		return (SuperstitiousHatModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return false;
	}
}
