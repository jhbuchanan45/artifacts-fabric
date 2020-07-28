package artifacts.common.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

abstract class RenderableCurio implements IRenderableCurio {

    @Override
    @Environment(EnvType.CLIENT)
    public void render(String identifier, int index, MatrixStack matrixStack, VertexConsumerProvider renderTypeBuffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        BipedEntityModel<LivingEntity> model = getModel();
        model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.animateModel(entity, limbSwing, limbSwingAmount, partialTicks);
        RenderHelper.followBodyRotations(entity, model);
        VertexConsumer vertexBuilder = ItemRenderer.getArmorVertexConsumer(renderTypeBuffer, model.getLayer(getTexture()), false, false);
        model.render(matrixStack, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }

    @Environment(EnvType.CLIENT)
    protected abstract BipedEntityModel<LivingEntity> getModel();

    @Environment(EnvType.CLIENT)
    protected abstract Identifier getTexture();
}
