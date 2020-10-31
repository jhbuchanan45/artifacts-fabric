package artifacts.common.trinkets;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.function.Predicate;

public final class TrinketsHelper {

	private TrinketsHelper() {}

	public static boolean isEquipped(Item item, LivingEntity entity) {
		return entity instanceof PlayerEntity && TrinketsApi.getTrinketsInventory((PlayerEntity) entity)
				.containsAny(Collections.singleton(item));
	}

	public static boolean isEquipped(Predicate<ItemStack> filter, LivingEntity entity) {
		if (entity instanceof PlayerEntity) {
			Inventory inventory = TrinketsApi.getTrinketsInventory((PlayerEntity) entity);
			for (int i = 0; i < inventory.size(); i++) {
				ItemStack stack = inventory.getStack(i);

				if (!stack.isEmpty() && filter.test(stack)) {
					return true;
				}
			}
		}

		return false;
	}
}
