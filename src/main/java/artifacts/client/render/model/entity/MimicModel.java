package artifacts.client.render.model.entity;

import artifacts.common.entity.MimicEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class MimicModel extends EntityModel<MimicEntity> {

    public ModelPart lid;
    public ModelPart chest;
    public ModelPart knob;
    public ModelPart upperTeeth;
    public ModelPart lowerTeeth;

    public MimicModel() {
        textureWidth = 128;
        textureHeight = 64;

        chest = new ModelPart(this, 0, 19);
        lowerTeeth = new ModelPart(this, 56, 15);
        lid = new ModelPart(this, 0, 0);
        upperTeeth = new ModelPart(this, 56, 0);
        knob = new ModelPart(this, 0, 0);

        chest.setPivot(0, 14, 7);
        lowerTeeth.setPivot(0, 14, 7);
        lid.setPivot(0, 15, 7);
        upperTeeth.setPivot(0, 15, 7);
        knob.setPivot(0, 15, 7);

        chest.addCuboid(-7, 0, -14, 14, 10, 14);
        lowerTeeth.addCuboid(-6, -3, -13, 12, 3, 12);
        lid.addCuboid(-7, -5, -14, 14, 5, 14);
        upperTeeth.addCuboid(-6, 0, -13, 12, 3, 12);
        knob.addCuboid(-1, -2, -15, 2, 4, 1);
    }

    @Override
    public void setAngles(MimicEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) { }

    @Override
    public void animateModel(MimicEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        if (entity.ticksInAir > 0) {
            float angle = Math.min(60, (entity.ticksInAir - 1 + tickDelta) * 6);
            lid.pitch = -angle * 0.0174533F;
            upperTeeth.pitch = -angle * 0.0174533F;
            knob.pitch = -angle * 0.0174533F;
            angle = Math.max(-30, (entity.ticksInAir - 1 + tickDelta) * -3F);
            chest.pitch = -angle * 0.0174533F;
            lowerTeeth.pitch = -angle * 0.0174533F;
        } else {
            lid.pitch = 0;
            upperTeeth.pitch = 0;
            knob.pitch = 0;
            chest.pitch = 0;
            lowerTeeth.pitch = 0;
        }
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        knob.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        chest.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        lowerTeeth.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        lid.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        upperTeeth.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
