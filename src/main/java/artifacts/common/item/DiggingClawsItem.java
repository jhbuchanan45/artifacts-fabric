package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ClawsModel;
import artifacts.common.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

public class DiggingClawsItem extends ArtifactItem {

    private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/digging_claws_default.png");
    private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/digging_claws_default.png");

    public DiggingClawsItem() {
        super(new Settings(), "digging_claws");
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new GloveCurio(this) {

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE_DEFAULT;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getSlimTexture() {
                return TEXTURE_SLIM;
            }

            @Environment(EnvType.CLIENT)
            protected ClawsModel getSlimModel() {
                if (model_slim == null) {
                    model_slim = new ClawsModel(true);
                }
                return (ClawsModel) model_slim;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected ClawsModel getModel() {
                if (model_default == null) {
                    model_default = new ClawsModel(false);
                }
                return (ClawsModel) model_default;
            }
        });
    }

    @Mod.EventBusSubscriber(modid = Artifacts.MOD_ID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
            if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CLAWS, event.getEntityLiving()).isPresent()) {
                event.setNewSpeed(event.getNewSpeed() + 4);
            }
        }

        @SubscribeEvent
        public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
            if (!event.canHarvest() && CuriosApi.getCuriosHelper().findEquippedCurio(Items.DIGGING_CLAWS, event.getEntityLiving()).isPresent()) {
                event.setCanHarvest(event.canHarvest() || event.getTargetBlock().getHarvestLevel() <= 2);
            }
        }
    }
}
