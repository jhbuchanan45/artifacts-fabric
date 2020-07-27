package artifacts.common.item;

import artifacts.client.render.model.curio.GloveModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.type.capability.ICurio;

public abstract class GloveCurio extends Curio {

    protected Object model_default;
    protected Object model_slim;

    public GloveCurio(Item item) {
        super(item);
    }

    @Environment(EnvType.CLIENT)
    protected static boolean hasSmallArms(Entity entity) {
        return entity instanceof AbstractClientPlayerEntity && ((AbstractClientPlayerEntity) entity).getModel().equals("slim");
    }

    @Environment(EnvType.CLIENT)
    protected Identifier getTexture(boolean smallArms) {
        return smallArms ? getSlimTexture() : getTexture();
    }

    @Environment(EnvType.CLIENT)
    protected abstract Identifier getSlimTexture();

    @Environment(EnvType.CLIENT)
    protected GloveModel getModel(boolean smallArms) {
        return (smallArms ? getSlimModel() : getModel());
    }

    @Environment(EnvType.CLIENT)
    protected GloveModel getSlimModel() {
        if (model_slim == null) {
            model_slim = new GloveModel(true);
        }
        return (GloveModel) model_slim;
    }

    @Override
    @Environment(EnvType.CLIENT)
    protected GloveModel getModel() {
        if (model_default == null) {
            model_default = new GloveModel(false);
        }
        return (GloveModel) model_default;
    }

    @Override
    public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
        return index == 0 || index == 1;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void render(String identifier, int index, MatrixStack matrixStack, VertexConsumerProvider renderTypeBuffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean smallArms = hasSmallArms(entity);
        GloveModel model = getModel(smallArms);
        model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.animateModel(entity, limbSwing, limbSwingAmount, partialTicks);
        ICurio.RenderHelper.followBodyRotations(entity, model);
        VertexConsumer vertexBuilder = ItemRenderer.getArmorVertexConsumer(renderTypeBuffer, model.getLayer(getTexture(smallArms)), false, false);
        model.renderHand(index == 0, matrixStack, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }
}
