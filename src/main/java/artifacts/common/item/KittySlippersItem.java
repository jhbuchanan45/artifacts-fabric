package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.KittySlippersModel;
import artifacts.common.events.PlayHurtSoundCallback;
import artifacts.common.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class KittySlippersItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/kitty_slippers.png");

	public KittySlippersItem() {
		super(new Settings());
		PlayHurtSoundCallback.EVENT.register(KittySlippersItem::onPlayHurtSound);
	}

	private static void onPlayHurtSound(LivingEntity entity, float volume, float pitch) {
		CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SLIPPERS, entity).ifPresent(curio -> {
			entity.playSound(SoundEvents.ENTITY_CAT_HURT, volume, pitch);
		});
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

/*    public static void onSetAttackTarget(LivingSetAttackTargetEvent event) {
        if (event.getEntityLiving() instanceof CreeperEntity && event.getTarget() != null) {
            if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SLIPPERS, event.getTarget()).isPresent()) {
                ((MobEntity) event.getEntityLiving()).setTarget(null);
            }
        }
    }

    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof CreeperEntity && event.getEntityLiving().getAttacker() != null) {
            if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SLIPPERS, event.getEntityLiving().getAttacker()).isPresent()) {
                event.getEntityLiving().setAttacker(null);
            }
        }
    }

    public static void onJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof CreeperEntity) {
            ((CreeperEntity) event.getEntity()).goalSelector.add(3, new FleeEntityGoal<>((CreeperEntity) event.getEntity(), PlayerEntity.class, (entity) -> entity != null && CuriosApi.getCuriosHelper().findEquippedCurio(Items.KITTY_SLIPPERS, entity).isPresent(), 6, 1, 1.3, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR::test));
        }
    }*/
}
