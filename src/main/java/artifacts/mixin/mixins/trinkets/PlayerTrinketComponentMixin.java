package artifacts.mixin.mixins.trinkets;

import artifacts.common.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.PlayerTrinketComponent;
import dev.emi.trinkets.api.TrinketSlots;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.function.BiFunction;

@Mixin(value = PlayerTrinketComponent.class, remap = false)
public abstract class PlayerTrinketComponentMixin {

	@Shadow
	public abstract Inventory getInventory();

	/**
	 * Prevent player from equipping two of the same Artifacts trinkets
	 */
	@Redirect(method = "equip", at = @At(value = "INVOKE", target = "Ljava/util/function/BiFunction;apply(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
	private Object canEquipNoDuplicate(BiFunction<TrinketSlots.Slot, ItemStack, Boolean> canEquip, Object _slot, Object _stack) {
		// Correct types
		ItemStack stack = (ItemStack) _stack;
		TrinketSlots.Slot slot = (TrinketSlots.Slot) _slot;

		boolean original = canEquip.apply(slot, stack);

		if (stack.getItem() instanceof TrinketArtifactItem) {
			return original && !this.getInventory().containsAny(Collections.singleton(stack.getItem()));
		}
		return original;
	}
}
