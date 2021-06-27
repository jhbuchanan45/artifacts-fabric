package artifacts.trinkets;

import artifacts.init.Components;
import artifacts.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class TrinketsHelper {

	private TrinketsHelper() {
	}

	public static boolean isEquipped(Item item, LivingEntity entity) {
		return isEquipped(item, entity, false);
	}

	public static boolean isEquipped(Predicate<ItemStack> filter, LivingEntity entity) {
		return isEquipped(filter, entity, false);
	}

	public static List<ItemStack> getAllEquipped(LivingEntity entity) {
		return getAllEquipped(entity, false);
	}

	public static boolean isEquipped(Item item, LivingEntity entity, boolean ignoreEffectsDisabled) {
		return isEquipped(stack -> stack.getItem().equals(item), entity, ignoreEffectsDisabled);
	}

	public static boolean isEquipped(Predicate<ItemStack> filter, LivingEntity entity, boolean ignoreEffectsDisabled) {
		for (ItemStack stack : getAllEquipped(entity, ignoreEffectsDisabled)) {
			if (filter.test(stack)) {
				return true;
			}
		}

		return false;
	}

	public static List<ItemStack> getAllEquipped(LivingEntity entity, boolean ignoreEffectsDisabled) {
		List<ItemStack> stacks = new ArrayList<>();

		// LivingEntity not currently supported by Trinkets
		if (entity instanceof PlayerEntity) {
			Inventory inventory = TrinketsApi.getTrinketsInventory((PlayerEntity) entity);

			for (int i = 0; i < inventory.size(); i++) {
				ItemStack stack = inventory.getStack(i);

				if (!stack.isEmpty() && stack.getItem() instanceof TrinketArtifactItem
						&& (Components.ARTIFACT_ENABLED.get(stack).get() || ignoreEffectsDisabled)) {
					stacks.add(stack);
				}
			}
		}

		return stacks;
	}
}
