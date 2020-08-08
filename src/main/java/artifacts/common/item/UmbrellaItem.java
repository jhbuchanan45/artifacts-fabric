package artifacts.common.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.UUID;

public class UmbrellaItem extends ArtifactItem {

	private static final EntityAttributeModifier UMBRELLA_SLOW_FALLING = new EntityAttributeModifier(UUID.fromString("a7a25453-2065-4a96-bc83-df600e13f390"), "artifacts:umbrella_slow_falling", -0.875, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

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

    /* TODO: reimplement
    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Artifacts.MOD_ID)
    public static class ClientEvents {

        @SubscribeEvent
        public static void onLivingRender(RenderLivingEvent.Pre<?, BipedEntityModel<?>> event) {
            LivingEntity entity = event.getEntity();
            if (!(entity.isUsingItem() && !entity.getActiveItem().isEmpty() && entity.getActiveItem().getItem().getUseAction(entity.getActiveItem()) == UseAction.BLOCK)) {
                boolean isHoldingOffHand = entity.getOffHandStack().getItem() == Items.UMBRELLA;
                boolean isHoldingMainHand = entity.getMainHandStack().getItem() == Items.UMBRELLA;
                if ((isHoldingMainHand && MinecraftClient.getInstance().options.mainArm == Arm.RIGHT) || (isHoldingOffHand && MinecraftClient.getInstance().options.mainArm == Arm.LEFT)) {
                    event.getRenderer().getModel().rightArmPose = BipedEntityModel.ArmPose.THROW_SPEAR;
                } else if ((isHoldingMainHand && MinecraftClient.getInstance().options.mainArm == Arm.LEFT) || (isHoldingOffHand && MinecraftClient.getInstance().options.mainArm == Arm.RIGHT)) {
                    event.getRenderer().getModel().leftArmPose = BipedEntityModel.ArmPose.THROW_SPEAR;
                }
            }
        }
    }*/
}
