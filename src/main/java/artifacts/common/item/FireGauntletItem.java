package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.RenderTypes;
import artifacts.client.render.model.curio.GloveModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class FireGauntletItem extends CurioArtifactItem {

	private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/fire_gauntlet_default.png");
	private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/fire_gauntlet_slim.png");
	private static final Identifier TEXTURE_DEFAULT_GLOW = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/fire_gauntlet_default_glow.png");
	private static final Identifier TEXTURE_SLIM_GLOW = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/fire_gauntlet_slim_glow.png");

	public FireGauntletItem() {
		super(new Settings());
	}

	@Override
	ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			@Override
			protected SoundEvent getEquipSound() {
				return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
			}
		};
	}

	@Override
	IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableGloveCurio() {
			@Override
			@Environment(EnvType.CLIENT)
			public void render(String identifier, int index, MatrixStack matrixStack, VertexConsumerProvider renderTypeBuffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				boolean smallArms = hasSmallArms(entity);
				GloveModel model = getModel(smallArms);
				model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				model.animateModel(entity, limbSwing, limbSwingAmount, partialTicks);
				RenderHelper.followBodyRotations(entity, model);
				VertexConsumer vertexBuilder = ItemRenderer.getArmorVertexConsumer(renderTypeBuffer, model.getLayer(getTexture(smallArms)), false, false);
				model.renderHand(index == 0, matrixStack, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
				vertexBuilder = ItemRenderer.getArmorVertexConsumer(renderTypeBuffer, RenderTypes.unlit(getGlowTexture(smallArms)), false, false);
				model.renderHand(index == 0, matrixStack, vertexBuilder, 0xF000F0, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
			}

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
			protected Identifier getGlowTexture(boolean smallArms) {
				return smallArms ? TEXTURE_SLIM_GLOW : TEXTURE_DEFAULT_GLOW;
			}
		};
	}

    /* TODO: reimplement
    @Mod.EventBusSubscriber(modid = Artifacts.MOD_ID)
    @SuppressWarnings("unused")
    public static class Events {

        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if (event.getSource() instanceof EntityDamageSource && !(event.getSource() instanceof ProjectileDamageSource) && !((EntityDamageSource) event.getSource()).isThorns()) {
                if (event.getSource().getAttacker() instanceof LivingEntity) {
                    LivingEntity attacker = (LivingEntity) event.getSource().getAttacker();
                    if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.FIRE_GAUNTLET, attacker).isPresent()) {
                        if (!event.getEntity().isFireImmune()) {
                            event.getEntity().setOnFireFor(8);
                        }
                    }
                }
            }
        }
    }*/
}
