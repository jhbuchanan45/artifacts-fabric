package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CrossNecklaceModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class CrossNecklaceItem extends TrinketArtifactItem {

	public static final double HURT_RESISTANCE_MULTIPLIER = 3; // Hurt invuln is multiplied by this factor
	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/cross_necklace.png");

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(CrossNecklaceModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new CrossNecklaceModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}
}
