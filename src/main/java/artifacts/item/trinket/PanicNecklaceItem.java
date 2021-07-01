package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.ObsidianSkullModel;
import artifacts.client.render.model.trinket.PanicNecklaceModel;
import artifacts.events.LivingEntityHurtCallback;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class PanicNecklaceItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/panic_necklace.png");

	public PanicNecklaceItem() {
		LivingEntityHurtCallback.EVENT.register(PanicNecklaceItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, DamageSource source, float amount) {
		if (!user.world.isClient && amount >= 1 && TrinketsHelper.isEquipped(Items.PANIC_NECKLACE, user)) {
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 160, 0, false, false));
		}
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(PanicNecklaceModel.getTexturedGloveData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new PanicNecklaceModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

}
