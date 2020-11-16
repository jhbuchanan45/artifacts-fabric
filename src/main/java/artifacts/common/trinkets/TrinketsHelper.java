package artifacts.common.trinkets;

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

	private TrinketsHelper() {}

	public static boolean isEquipped(Item item, LivingEntity entity) {
		return entity instanceof PlayerEntity && TrinketsHelper.isEquipped(stack -> stack.getItem() == item, entity);
	}

	public static boolean isEquipped(Predicate<ItemStack> filter, LivingEntity entity) {
		for (ItemStack stack : TrinketsHelper.getAllEquipped(entity)) {
			if (!stack.isEmpty() && filter.test(stack)) {
				return true;
			}
		}

		return false;
	}

	public static List<ItemStack> getAllEquipped(LivingEntity entity) {
		List<ItemStack> stacks = new ArrayList<>();

		if (entity instanceof PlayerEntity) {
			Inventory inventory = TrinketsApi.getTrinketsInventory((PlayerEntity) entity);

			for (int i = 0; i < inventory.size(); i++) {
				ItemStack stack = inventory.getStack(i);
				stacks.add(stack);
			}
		}

		return stacks;
	}
}
