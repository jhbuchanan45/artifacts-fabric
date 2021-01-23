package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.FlippersModel;
import artifacts.common.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class FlippersItem extends TrinketArtifactItem {

	public static final int SWIM_SPEED_MULTIPLIER = 2;
	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/flippers.png");

	public FlippersItem() {
		super(new Item.Settings());
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new FlippersModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
