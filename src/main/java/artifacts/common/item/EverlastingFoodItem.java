package artifacts.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EverlastingFoodItem extends ArtifactItem {

    public EverlastingFoodItem(Settings properties) {
        super(properties);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
        if (isFood()) {
            entity.eatFood(world, stack.copy());
            if (!world.isClient && entity instanceof PlayerEntity) {
                ((PlayerEntity) entity).getItemCooldownManager().set(this, 800);
            }
        }
        return stack;
    }
}
