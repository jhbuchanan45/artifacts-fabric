package artifacts.item.trinket.pendant;

import artifacts.client.render.model.trinket.ClawsModel;
import artifacts.client.render.model.trinket.GloveModel;
import artifacts.client.render.model.trinket.PendantModel;
import artifacts.events.LivingEntityAttackedCallback;
import artifacts.item.trinket.TrinketArtifactItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public abstract class PendantItem extends TrinketArtifactItem {

	private final Identifier texture;

	public PendantItem(Identifier texture, LivingEntityAttackedCallback callback) {
		this.texture = texture;
		LivingEntityAttackedCallback.EVENT.register(callback);
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new PendantModel(TexturedModelData.of(PendantModel.getTexturedModelData(Dilation.NONE), 64, 64).createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new PendantModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return texture;
	}
}
