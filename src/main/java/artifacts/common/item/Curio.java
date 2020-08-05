package artifacts.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.ICurio;

public class Curio implements ICurio {

	private final Item curioItem;
	private final SoundEvent equipSound = getEquipSound();

	public Curio(Item item) {
		curioItem = item;
	}

	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
	}

	@Override
	public void playRightClickEquipSound(LivingEntity entity) {
		entity.world.playSound(null, new BlockPos(entity.getPos()), equipSound, SoundCategory.NEUTRAL, 1, 1);
	}

	public boolean canRightClickEquip() {
		return true;
	}

	@Override
	public boolean canEquip(String identifier, LivingEntity entity) {
		// Only one of the same artifacts can be equiped at a time
		return !CuriosApi.getCuriosHelper().findEquippedCurio(curioItem, entity).isPresent();
	}

	/**
	 * Used to give a Curio a permanent status effect while wearing it.
	 * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
	 *
	 * @return The StatusEffectInstance to be applied
	 */
	public StatusEffectInstance getPermanentEffect() {
		return null;
	}
}
