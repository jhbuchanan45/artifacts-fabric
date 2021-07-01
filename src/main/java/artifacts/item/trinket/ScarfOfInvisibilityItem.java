package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.ScarfModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;

public class ScarfOfInvisibilityItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/scarf_of_invisibility.png");

	@Override
	public StatusEffectInstance getPermanentEffect() {
		return new StatusEffectInstance(StatusEffects.INVISIBILITY, 20, 0, true, false);
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(ScarfModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new ScarfModel(root, RenderLayer::getEntityTranslucent);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

}
