package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.common.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class PocketPistonItem extends CurioArtifactItem {

    private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/pocket_piston_default.png");
    private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/pocket_piston_slim.png");

    public PocketPistonItem() {
        super(new Settings());
    }

    @Override
    ICurio attachCurio(ItemStack stack) {
        return new Curio(this);
    }

    @Override
    IRenderableCurio attachRenderableCurio(ItemStack stack) {
        return new RenderableGloveCurio() {
            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getSlimTexture() {
                return TEXTURE_SLIM;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE_DEFAULT;
            }
        };
    }

    /* TODO: reimplement
    @Mod.EventBusSubscriber(modid = Artifacts.MOD_ID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onLivingAttack(LivingAttackEvent event) {
            if (event.getSource().getAttacker() instanceof LivingEntity && CuriosApi.getCuriosHelper().findEquippedCurio(Items.POCKET_PISTON, (LivingEntity) event.getSource().getAttacker()).isPresent()) {
                LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                event.getEntityLiving().takeKnockback(1.5F, MathHelper.sin((float) (attacker.yaw * (Math.PI / 180))), -MathHelper.cos((float) (attacker.yaw * (Math.PI / 180))));
            }
        }
    }*/
}
