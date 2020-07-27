package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.UniversalAttractorModel;
import artifacts.common.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

public class UniversalAttractorItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/universal_attractor.png");

    public UniversalAttractorItem() {
        super(new Settings());
    }

    public static int getCooldown(ItemStack stack) {
        return stack.getOrCreateTag().getInt("Cooldown");
    }

    public static void setCooldown(ItemStack stack, int cooldown) {
        stack.getOrCreateTag().putInt("Cooldown", cooldown);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            // magnet logic from Botania, see https://github.com/Vazkii/Botania
            @Override
            public void curioTick(String identifier, int index, LivingEntity entity) {
                if (entity.isSpectator() || !(entity instanceof PlayerEntity)) {
                    return;
                }

                int cooldown = getCooldown(stack);
                if (cooldown <= 0) {
                    Vec3d playerPos = entity.getPos().add(0, 0.75, 0);

                    int range = 5;
                    List<ItemEntity> items = entity.world.getNonSpectatingEntities(ItemEntity.class, new Box(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
                    int pulled = 0;
                    for (ItemEntity item : items) {
                        if (item.isAlive() && !item.cannotPickup() && !item.getPersistentData().getBoolean("PreventRemoteMovement")) {
                            if (pulled++ > 200) {
                                break;
                            }

                            Vec3d motion = playerPos.subtract(item.getPos().add(0, item.getHeight() / 2, 0));
                            if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                motion = motion.normalize();
                            }
                            item.setVelocity(motion.multiply(0.6));
                        }
                    }
                } else {
                    setCooldown(stack, cooldown - 1);
                }
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected UniversalAttractorModel getModel() {
                if (model == null) {
                    model = new UniversalAttractorModel();
                }
                return (UniversalAttractorModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        });
    }

    @Mod.EventBusSubscriber(modid = Artifacts.MOD_ID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onItemToss(ItemTossEvent event) {
            CuriosApi.getCuriosHelper().findEquippedCurio(Items.UNIVERSAL_ATTRACTOR, event.getPlayer()).ifPresent((triple) -> setCooldown(triple.right, 100));
        }
    }
}
