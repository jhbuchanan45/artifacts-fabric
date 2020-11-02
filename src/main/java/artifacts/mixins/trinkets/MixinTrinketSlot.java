package artifacts.mixins.trinkets;

import artifacts.common.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.TrinketSlots;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.function.BiFunction;

@Mixin(value = TrinketSlot.class, remap = false)
public abstract class MixinTrinketSlot extends Slot {

	public MixinTrinketSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	/**
	 * Prevent player from equipping two of the same Artifacts trinkets
	 */
	// Overridden vanilla method so remap must be on
	@SuppressWarnings("DefaultAnnotationParam")
	@Redirect(method = "canInsert", remap = true, at = @At(value = "INVOKE", target = "Ljava/util/function/BiFunction;apply(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
	private Object canEquipNoDuplicate(BiFunction<TrinketSlots.Slot, ItemStack, Boolean> canEquip, Object _slot, Object _stack) {
		// Correct types
		ItemStack stack = (ItemStack) _stack;
		TrinketSlots.Slot slot = (TrinketSlots.Slot) _slot;

		boolean original = canEquip.apply(slot, stack);

		if (stack.getItem() instanceof TrinketArtifactItem) {
			return original && !this.inventory.containsAny(Collections.singleton(stack.getItem()));
		}
		return original;
	}
}
