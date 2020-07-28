package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.KittySlippersModel;
import artifacts.common.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class KittySlippersItem extends CurioArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/kitty_slippers.png");

    public KittySlippersItem() {
        super(new Settings());
    }

    @Override
    ICurio attachCurio(ItemStack stack) {
        return new Curio(this);
    }

    @Override
    IRenderableCurio attachRenderableCurio(ItemStack stack) {
        return new RenderableCurio() {
            private Object model;

            @Override
            @Environment(EnvType.CLIENT)
            protected KittySlippersModel getModel() {
                if (model == null) {
                    model = new KittySlippersModel();
                }
                return (KittySlippersModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        };
    }

    /* TODO: reimplement
    @Mod.EventBusSubscriber(modid = Artifacts.MOD_ID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent event) {
            if (event.getEntityLiving() instanceof PlayerEntity && CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SLIPPERS, event.getEntityLiving()).isPresent()) {
                event.getEntity().world.playSound(null, event.getEntityLiving().getX(), event.getEntityLiving().getY(), event.getEntityLiving().getZ(), SoundEvents.ENTITY_CAT_HURT, SoundCategory.PLAYERS, 1, (event.getEntityLiving().getRandom().nextFloat() - event.getEntityLiving().getRandom().nextFloat()) * 0.2F + 1.0F);
            }
        }

        @SubscribeEvent
        public static void onSetAttackTarget(LivingSetAttackTargetEvent event) {
            if (event.getEntityLiving() instanceof CreeperEntity && event.getTarget() != null) {
                if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SLIPPERS, event.getTarget()).isPresent()) {
                    ((MobEntity) event.getEntityLiving()).setTarget(null);
                }
            }
        }

        @SubscribeEvent
        public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
            if (event.getEntityLiving() instanceof CreeperEntity && event.getEntityLiving().getAttacker() != null) {
                if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SLIPPERS, event.getEntityLiving().getAttacker()).isPresent()) {
                    event.getEntityLiving().setAttacker(null);
                }
            }
        }

        @SubscribeEvent
        public static void onJoinWorld(EntityJoinWorldEvent event) {
            if (event.getEntity() instanceof CreeperEntity) {
                ((CreeperEntity) event.getEntity()).goalSelector.add(3, new FleeEntityGoal<>((CreeperEntity) event.getEntity(), PlayerEntity.class, (entity) -> entity != null && CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SLIPPERS, entity).isPresent(), 6, 1, 1.3, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR::test));
            }
        }
    }*/
}
