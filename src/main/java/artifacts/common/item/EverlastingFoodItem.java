package artifacts.common.item;

import artifacts.Artifacts;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EverlastingFoodItem extends ArtifactItem {

	public EverlastingFoodItem(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
		if (isFood()) {
			entity.eatFood(world, stack.copy());
			if (!world.isClient && entity instanceof PlayerEntity) {
				((PlayerEntity) entity).getItemCooldownManager().set(this, Artifacts.CONFIG.general.everlastingFoodCooldown);
			}
		}
		return stack;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 24;
	}
}
