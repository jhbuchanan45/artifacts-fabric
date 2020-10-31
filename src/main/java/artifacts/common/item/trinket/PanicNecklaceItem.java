package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.PanicNecklaceModel;
import artifacts.common.events.UserHurtCallback;
import artifacts.common.init.Items;
import artifacts.common.util.TrinketsHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class PanicNecklaceItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/panic_necklace.png");
	private Object model;

	public PanicNecklaceItem() {
		super(new Item.Settings());
		UserHurtCallback.EVENT.register(PanicNecklaceItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, DamageSource source, float amount) {
		if (!user.world.isClient && amount >= 1 && TrinketsHelper.isEquipped(Items.PANIC_NECKLACE, user)) {
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 160, 0, false, false));
		}
	}

	@Override
	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> getModel() {
		if (model == null) {
			model = new PanicNecklaceModel();
		}
		return (PanicNecklaceModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return super.canWearInSlot(group, slot);
	}
}
