package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ClawsModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class DiggingClawsItem extends CurioArtifactItem {

	public static final int NEW_BASE_MINING_LEVEL = 2;
	private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/digging_claws_default.png");
	private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/digging_claws_default.png");

	public DiggingClawsItem() {
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
				if (modelSlim == null) {
					modelSlim = new ClawsModel(true);
				}
				return (ClawsModel) modelSlim;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected ClawsModel getModel() {
				if (modelDefault == null) {
					modelDefault = new ClawsModel(false);
				}
				return (ClawsModel) modelDefault;
			}
		};
	}

    /* TODO: reimplement (see https://fabricmc.net/wiki/tutorial:tools and https://fabricmc.net/wiki/tutorial:mining_levels)
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
    }*/
}
