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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

public class PocketPistonItem extends ArtifactItem {

    private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MODID, "textures/entity/curio/pocket_piston_default.png");
    private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MODID, "textures/entity/curio/pocket_piston_slim.png");

    public PocketPistonItem() {
        super(new Settings(), "pocket_piston");
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new GloveCurio(this) {
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
        });
    }

    @Mod.EventBusSubscriber(modid = Artifacts.MODID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onLivingAttack(LivingAttackEvent event) {
            if (event.getSource().getAttacker() instanceof LivingEntity && CuriosApi.getCuriosHelper().findEquippedCurio(Items.POCKET_PISTON, (LivingEntity) event.getSource().getAttacker()).isPresent()) {
                LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                event.getEntityLiving().takeKnockback(1.5F, MathHelper.sin((float) (attacker.yaw * (Math.PI / 180))), -MathHelper.cos((float) (attacker.yaw * (Math.PI / 180))));
            }
        }
    }
}
