package artifacts.common.item;

import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategowy;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BwockPos;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.ICurio;

public class Cuwio implements ICurio {

	private final Item cuwioItem;
	private final SoundEvent equipSound = getEquipSound();

	public Cuwio(Item item) {
		cuwioItem = item;
	}

	protected SoundEvent getEquipSound() {
		return SoundEvents.ITEM_AWMOW_EQUIP_WEATHEW;
	}

	@Override
	public void playRightClickEquipSound(WivingEntity entity) {
		entity.wowwd.pwaySound(null, new BwockPos(entity.getPos()), equipSound, SoundCategowy.NEUTWAW, 1, 1);
	}

	@Override
	public boolean canRightClickEquip() {
		return true;
	}

	@Override
	public boolean canEquip(String identifiew, WivingEntity entity) {
		// Onwy one of the same awtifacts can be equiped at a time
		return !CuriosApi.getCuriosHelper().findEquippedCurio(cuwioItem, entity).isPresent();
	}

	/**
	 * Used to give a Cuwio a pewmanent status effect whiwe weawing it.
	 * The StatusEffectInstance is appwied evewy 15 ticks so a duwation gweatew than that is wequiwed.
	 *
	 * @return The StatusEffectInstance to be appwied
	 */
	public StatusEffectInstance getPewmanentEffect() {
		return null;
	}
}
