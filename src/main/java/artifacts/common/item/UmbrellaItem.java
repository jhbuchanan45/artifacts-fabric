package artifacts.common.item;

import artifacts.common.init.Items;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class UmbrellaItem extends ArtifactItem {

	public UmbrellaItem() {
		super(new Settings());
		DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
	}

    /* TODO: wait for fapi/lib
    @Override
    public boolean isShield(ItemStack stack, LivingEntity entity) {
        return true;
    }*/

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getStackInHand(hand);
		player.setCurrentHand(hand);
		return TypedActionResult.consume(itemstack);
	}

	public static HeldStatus getHeldStatusForHand(LivingEntity entity, Hand hand) {
		if (entity.getStackInHand(hand).getItem() != Items.UMBRELLA) {
			return HeldStatus.NONE;
		}

		if (entity.isUsingItem() && entity.getActiveHand() == hand && !entity.getActiveItem().isEmpty()
				&& entity.getActiveItem().getUseAction() == UseAction.BLOCK) {
			return HeldStatus.BLOCKING;
		}

		return HeldStatus.HELD_UP;
	}

	public enum HeldStatus {
		NONE,
		HELD_UP,
		BLOCKING
	}
}
