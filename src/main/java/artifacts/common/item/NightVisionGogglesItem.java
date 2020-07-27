package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.RenderTypes;
import artifacts.client.render.model.curio.NightVisionGogglesModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

public class NightVisionGogglesItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/night_vision_goggles.png");
    private static final Identifier TEXTURE_GLOW = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/night_vision_goggles_glow.png");

    public NightVisionGogglesItem() {
        super(new Item.Settings());
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (!livingEntity.world.isClient && livingEntity.age % 15 == 0) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 319, 0, true, false));
                }
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected NightVisionGogglesModel getModel() {
                if (model == null) {
                    model = new NightVisionGogglesModel();
                }
                return (NightVisionGogglesModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }

            @Override
            @Environment(EnvType.CLIENT)
            public void render(String identifier, int index, MatrixStack matrixStack, VertexConsumerProvider renderTypeBuffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                super.render(identifier, index, matrixStack, renderTypeBuffer, light, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer buffer = ItemRenderer.getArmorVertexConsumer(renderTypeBuffer, RenderTypes.unlit(TEXTURE_GLOW), false, false);
                getModel().render(matrixStack, buffer, 0xF000F0, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
            }
        });
    }
}
