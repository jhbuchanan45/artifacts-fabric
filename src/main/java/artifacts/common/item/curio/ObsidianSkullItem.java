package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ObsidianSkullModel;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class ObsidianSkullItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/obsidian_skull.png");

	public ObsidianSkullItem() {
		super(new Item.Settings());
	}

	@Override
	protected IRenderableCurio attachRenderableCurio(ItemStack stack) {
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
