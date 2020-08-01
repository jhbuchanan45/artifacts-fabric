package artifacts.common.item;

import artifacts.client.render.model.curio.PendantModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class PendantItem extends CurioArtifactItem {

	private final Identifier texture;

	public PendantItem(Identifier texture) {
		super(new Settings());
		this.texture = texture;
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
			protected PendantModel getModel() {
				if (model == null) {
					model = new PendantModel();
				}
				return (PendantModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return texture;
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
                if (event.getSource() == DamageSource.LIGHTNING_BOLT && CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, event.getEntityLiving()).isPresent()) {
                    event.setCanceled(true);
                } else if (event.getSource().getAttacker() instanceof LivingEntity) {
                    if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, event.getEntityLiving()).isPresent()) {
                        LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                        if (attacker.world.isSkyVisible(new BlockPos(attacker.getPos())) && event.getEntityLiving().getRandom().nextFloat() < 0.25F) {
                            LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(attacker.world);
                            if (lightningBolt != null) {
                                lightningBolt.method_29495(Vec3d.ofBottomCenter(new BlockPos(attacker.getPos())));
                                lightningBolt.setChanneler(attacker instanceof ServerPlayerEntity ? (ServerPlayerEntity) attacker : null);
                                attacker.world.spawnEntity(lightningBolt);
                            }
                        }
                    }
                    if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.FLAME_PENDANT, event.getEntityLiving()).isPresent()) {
                        LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                        if (!attacker.isFireImmune() && attacker.isMobOrPlayer() && event.getEntityLiving().getRandom().nextFloat() < 0.40F) {
                            attacker.setOnFireFor(8);
                            attacker.damage(new EntityDamageSource("onFire", event.getEntity()).setFire(), 2);
                        }
                    }
                    if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.THORN_PENDANT, event.getEntityLiving()).isPresent()) {
                        LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                        if (attacker.isMobOrPlayer() && RANDOM.nextFloat() < 0.5F) {
                            attacker.damage(DamageSource.thorns(event.getEntity()), 2 + event.getEntityLiving().getRandom().nextInt(5));
                        }
                    }
                }
            }
        }
    }*/
}
