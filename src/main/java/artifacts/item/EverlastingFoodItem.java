package artifacts.item;

import artifacts.Artifacts;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EverlastingFoodItem extends ArtifactItem {

	public EverlastingFoodItem(FoodComponent foodComponent) {
		super(new Settings().food(foodComponent));
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
		if (!world.isClient && entity instanceof PlayerEntity) {
			((PlayerEntity) entity).getItemCooldownManager().set(this, Artifacts.CONFIG.general.everlastingFoodCooldown);
		}

		// Stack decrement is cancelled in LivingEntity.eatFood() mixin
		return super.finishUsing(stack, world, entity);
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 24;
	}
}
