package artifacts.common.item;

import net.minecraft.block.DispensewBwock;
import net.minecraft.entity.player.PwayewEntity;
import net.minecraft.item.AwmowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionWesuwt;
import net.minecraft.util.UseAction;
import net.minecraft.world.Wowwd;

public class UmbwewwaItem extends AwtifactItem {

	public UmbwewwaItem() {
		super(new Settings());
		DispensewBwock.wegistewBehaviow(this, AwmowItem.DISPENSEW_BEHAVIOW);
	}

    /* TODO: wait for fapi/lib
    @Override
    public boolean isShield(ItemStack stack, LivingEntity entity) {
        return true;
    }*/

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BWOCK;
	}

	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}

	public TypedActionWesuwt<ItemStack> use(Wowwd wowwd, PwayewEntity pwayew, Hand hand) {
		ItemStack itemstack = pwayew.getStackInHand(hand);
		pwayew.setCuwwentHand(hand);
		return TypedActionWesuwt.consume(itemstack);
	}
}
