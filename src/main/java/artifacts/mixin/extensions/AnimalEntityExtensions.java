package artifacts.mixin.extensions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface AnimalEntityExtensions {

	boolean artifacts$isBreedingItemWithCooldown(ItemStack stack, PlayerEntity player);
}
