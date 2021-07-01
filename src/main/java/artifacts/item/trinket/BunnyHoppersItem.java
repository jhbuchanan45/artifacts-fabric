package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.BunnyHoppersModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class BunnyHoppersItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/bunny_hoppers.png");

	@Override
	public StatusEffectInstance getPermanentEffect() {
		return new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20, 1, true, false);
	}

	@Override
	protected SoundEvent getExtraHurtSound() {
		return SoundEvents.ENTITY_RABBIT_HURT;
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new BunnyHoppersModel(BunnyHoppersModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new BunnyHoppersModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

}
