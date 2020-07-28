package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.BunnyHoppersModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class BunnyHoppersItem extends CurioArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/bunny_hoppers.png");

    public BunnyHoppersItem() {
        super(new Settings());
    }

    @Override
    ICurio attachCurio(ItemStack stack) {
        return new Curio(this) {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (!livingEntity.world.isClient && livingEntity.age % 15 == 0) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 39, 1, true, false));
                }
            }
        };
    }

    @Override
    IRenderableCurio attachRenderableCurio(ItemStack stack) {
        return new RenderableCurio() {
            private Object model;

            @Override
            @Environment(EnvType.CLIENT)
            protected BunnyHoppersModel getModel() {
                if (model == null) {
                    model = new BunnyHoppersModel();
                }
                return (BunnyHoppersModel) model;
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
            if (event.getEntityLiving() instanceof PlayerEntity && CuriosApi.getCuriosHelper().findEquippedCurio(Items.BUNNY_HOPPERS, event.getEntityLiving()).isPresent()) {
                event.getEntity().world.playSound(null, event.getEntityLiving().getX(), event.getEntityLiving().getY(), event.getEntityLiving().getZ(), SoundEvents.ENTITY_RABBIT_HURT, SoundCategory.PLAYERS, 1, (event.getEntityLiving().getRandom().nextFloat() - event.getEntityLiving().getRandom().nextFloat()) * 0.2F + 1.0F);
            }
        }
    }*/
}
