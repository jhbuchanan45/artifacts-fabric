package artifacts.item;

import artifacts.init.Items;
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

	public static boolean isHeldUpInEitherHand(LivingEntity entity) {
		for (Hand hand : Hand.values()) {
			if (getHeldStatusForHand(entity, hand) == HeldStatus.HELD_UP) {
				return true;
			}
		}

		return false;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getStackInHand(hand);
		player.setCurrentHand(hand);
		return TypedActionResult.consume(itemstack);
	}

	public enum HeldStatus {
		NONE,
		HELD_UP,
		BLOCKING
	}
}
