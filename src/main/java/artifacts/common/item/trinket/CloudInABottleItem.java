package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CloudInABottleModel;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class CloudInABottleItem extends TrinketArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/cloud_in_a_bottle.png");
    private Object model;

    public CloudInABottleItem() {
        super(new Settings());
    }

/*    public static void jump(PlayerEntity player) {
        double upwardsMotion = 0.5;
        if (player.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
            // noinspection ConstantConditions
            upwardsMotion += 0.1 * (player.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1);
        }
        upwardsMotion *= player.isSprinting() ? 1.5 : 1;

        Vec3d motion = player.getVelocity();
        double motionMultiplier = player.isSprinting() ? 0.65 : 0;
        float direction = (float) (player.yaw * Math.PI / 180);
        player.setVelocity(player.getVelocity().add(
                -MathHelper.sin(direction) * motionMultiplier,
                upwardsMotion - motion.y,
                MathHelper.cos(direction) * motionMultiplier)
        );

        player.velocityDirty = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(player);

        player.incrementStat(Stats.JUMP);
        if (player.isSprinting()) {
            player.addExhaustion(0.2F);
        } else {
            player.addExhaustion(0.05F);
        }
    }*/

    @Override
    protected SoundEvent getEquipSound() {
        return SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH;
    }

    @Override
    protected Identifier getTexture() {
        return TEXTURE;
    }

    @Override
    @Environment(EnvType.CLIENT)
    protected CloudInABottleModel getModel() {
        if (model == null) {
            model = new CloudInABottleModel();
        }
        return (CloudInABottleModel) model;
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
    }

    /*private class DoubleJumpHandler {

        @Environment(EnvType.CLIENT)
        private boolean canDoubleJump;

        @Environment(EnvType.CLIENT)
        private boolean hasReleasedJumpKey;

        @SubscribeEvent
        @Environment(EnvType.CLIENT)
        @SuppressWarnings("unused")
        public void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;

                if (player != null) {
                    if ((player.isOnGround() || player.isClimbing()) && !player.isTouchingWater()) {
                        hasReleasedJumpKey = false;
                        canDoubleJump = true;
                    } else {
                        if (!player.input.jumping) {
                            hasReleasedJumpKey = true;
                        } else {
                            if (!player.abilities.flying && canDoubleJump && hasReleasedJumpKey) {
                                canDoubleJump = false;
                                CuriosApi.getCuriosHelper().findEquippedCurio(CloudInABottleItem.this, player).ifPresent(stack -> {

                                    NetworkHandler.INSTANCE.sendToServer(new DoubleJumpPacket());
                                    jump(player);
                                    player.fallDistance = 0;
                                    if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.WHOOPEE_CUSHION, player).isPresent()) {
                                        player.playSound(artifacts.common.init.SoundEvents.FART, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                                    } else {
                                        player.playSound(SoundEvents.BLOCK_WOOL_FALL, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }*/
}
