package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.VillagerHatModel;
import artifacts.client.render.model.trinket.WhoopeeCushionModel;
import artifacts.init.SoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class WhoopeeCushionItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/whoopee_cushion.png");

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.FART;
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(WhoopeeCushionModel.getTexturedGloveData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new WhoopeeCushionModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}
}
