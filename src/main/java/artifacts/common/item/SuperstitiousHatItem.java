package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.SuperstitiousHatModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class SuperstitiousHatItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/superstitious_hat.png");

	public SuperstitiousHatItem() {
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
			protected SuperstitiousHatModel getModel() {
				if (model == null) {
					model = new SuperstitiousHatModel();
				}
				return (SuperstitiousHatModel) model;
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
        public static void onLootingLevel(LootingLevelEvent event) {
            Entity killerEntity = event.getDamageSource().getAttacker();
            if (killerEntity instanceof LivingEntity && CuriosApi.getCuriosHelper().findEquippedCurio(Items.SUPERSTITIOUS_HAT, (LivingEntity) killerEntity).isPresent()) {
                event.setLootingLevel(event.getLootingLevel() + 1);
            }
        }
    }*/
}
