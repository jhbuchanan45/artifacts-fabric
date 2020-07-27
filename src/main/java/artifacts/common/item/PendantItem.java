package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.PendantModel;
import artifacts.common.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

public class PendantItem extends ArtifactItem {

    private final Identifier texture;

    public PendantItem(String name) {
        super(new Settings(), name);
        texture = new Identifier(Artifacts.MODID, String.format("textures/entity/curio/%s.png", name));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            protected SoundEvent getEquipSound() {
                return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected PendantModel getModel() {
                if (model == null) {
                    model = new PendantModel();
                }
                return (PendantModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return texture;
            }
        });
    }

    @Mod.EventBusSubscriber(modid = Artifacts.MODID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if (!event.getEntity().world.isClient && event.getAmount() >= 1) {
                if (event.getSource() == DamageSource.LIGHTNING_BOLT && CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, event.getEntityLiving()).isPresent()) {
                    event.setCanceled(true);
                } else if (event.getSource().getAttacker() instanceof LivingEntity) {
                    if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, event.getEntityLiving()).isPresent()) {
                        LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                        if (attacker.world.isSkyVisible(new BlockPos(attacker.getPos())) && event.getEntityLiving().getRandom().nextFloat() < 0.25F) {
                            LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(attacker.world);
                            if (lightningBolt != null) {
                                lightningBolt.method_29495(Vec3d.ofBottomCenter(new BlockPos(attacker.getPos())));
                                lightningBolt.setChanneler(attacker instanceof ServerPlayerEntity ? (ServerPlayerEntity) attacker : null);
                                attacker.world.spawnEntity(lightningBolt);
                            }
                        }
                    }
                    if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.FLAME_PENDANT, event.getEntityLiving()).isPresent()) {
                        LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                        if (!attacker.isFireImmune() && attacker.isMobOrPlayer() && event.getEntityLiving().getRandom().nextFloat() < 0.40F) {
                            attacker.setOnFireFor(8);
                            attacker.damage(new EntityDamageSource("onFire", event.getEntity()).setFire(), 2);
                        }
                    }
                    if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.THORN_PENDANT, event.getEntityLiving()).isPresent()) {
                        LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                        if (attacker.isMobOrPlayer() && RANDOM.nextFloat() < 0.5F) {
                            attacker.damage(DamageSource.thorns(event.getEntity()), 2 + event.getEntityLiving().getRandom().nextInt(5));
                        }
                    }
                }
            }
        }
    }
}
