package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.PanicNecklaceModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class PanicNecklaceItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/panic_necklace.png");

	public PanicNecklaceItem() {
		super(new Settings());
	}

	@Override
	ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			@Override
			protected SoundEvent getEquipSound() {
				return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
			}
		};
	}

	@Override
	IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableCurio() {
			private Object model;

			@Override
			@Environment(EnvType.CLIENT)
			protected BipedEntityModel<LivingEntity> getModel() {
				if (model == null) {
					model = new PanicNecklaceModel();
				}
				return (PanicNecklaceModel) model;
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
            if (!event.getEntity().world.isClient && event.getAmount() >= 1) {
                if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.PANIC_NECKLACE, event.getEntityLiving()).isPresent()) {
                    event.getEntityLiving().addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 160, 0, false, false));
                }
            }
        }
    }*/
}
