package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ObsidianSkullModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class ObsidianSkullItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/obsidian_skull.png");

	public ObsidianSkullItem() {
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
			protected ObsidianSkullModel getModel() {
				if (model == null) {
					model = new ObsidianSkullModel();
				}
				return (ObsidianSkullModel) model;
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
        public static void onLivingHurt(LivingHurtEvent event) {
            if (!event.getEntity().world.isClient && event.getAmount() >= 1 && (event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.IN_FIRE || event.getSource() == DamageSource.LAVA) && event.getEntity() instanceof PlayerEntity) {
                if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.OBSIDIAN_SKULL, event.getEntityLiving()).isPresent() && !((PlayerEntity) event.getEntity()).getItemCooldownManager().isCoolingDown(Items.OBSIDIAN_SKULL)) {
                    event.getEntityLiving().addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 0, false, true));
                    ((PlayerEntity) event.getEntity()).getItemCooldownManager().set(Items.OBSIDIAN_SKULL, 1200);
                }
            }
        }
    }*/
}
