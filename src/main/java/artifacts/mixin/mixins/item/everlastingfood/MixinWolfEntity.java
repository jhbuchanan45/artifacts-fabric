package artifacts.mixin.mixins.item.everlastingfood;

import artifacts.Artifacts;
import artifacts.common.item.EverlastingFoodItem;
import artifacts.mixin.extensions.AnimalEntityExtensions;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(WolfEntity.class)
public abstract class MixinWolfEntity {

	@Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/WolfEntity;isBreedingItem(Lnet/minecraft/item/ItemStack;)Z"))
	private boolean cooldownBreedingItem(WolfEntity wolfEntity, ItemStack stack, PlayerEntity player) {
		return ((AnimalEntityExtensions) this).artifacts$isBreedingItemWithCooldown(stack, player);
	}

	@Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"),
			slice = @Slice(to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/WolfEntity;heal(F)V")))
	private void cancelDecrement(ItemStack stack, int amount, PlayerEntity player) {
		if (stack.getItem() instanceof EverlastingFoodItem) {
			player.getItemCooldownManager().set(stack.getItem(), Artifacts.CONFIG.general.everlastingFoodCooldown);
		} else {
			stack.decrement(amount);
		}
	}
}
