package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.HeliumFlamingoModel;
import artifacts.init.Components;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import be.florens.expandability.api.fabric.PlayerSwimCallback;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;


public class HeliumFlamingoItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/helium_flamingo.png");

	public HeliumFlamingoItem() {
		super();
		PlayerSwimCallback.EVENT.register(HeliumFlamingoItem::onPlayerSwim);
	}

	private static ActionResult onPlayerSwim(PlayerEntity player) {
		return TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, player) ? ActionResult.SUCCESS : ActionResult.PASS;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new HeliumFlamingoModel();
	}

	@Override
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ENTITY_ITEM_PICKUP, 1f, 0.7f);
	}

	public static boolean isFlying(LivingEntity entity) {
		return TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, entity)
				&& entity instanceof PlayerEntity
				&& Components.ARTIFACT_ABILITIES.get((PlayerEntity) entity).isAirSwimming();
	}
}
