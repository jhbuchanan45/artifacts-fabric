package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.HeliumFlamingoModel;
import artifacts.client.render.model.trinket.KittySlippersModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class KittySlippersItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/kitty_slippers.png");

	@Override
	protected SoundEvent getExtraHurtSound() {
		return SoundEvents.ENTITY_CAT_HURT;
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(KittySlippersModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new KittySlippersModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ENTITY_CAT_AMBIENT;
	}
}
