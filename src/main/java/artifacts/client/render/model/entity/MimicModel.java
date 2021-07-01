package artifacts.client.render.model.entity;

import artifacts.entity.MimicEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class MimicModel extends EntityModel<MimicEntity> {

	protected final ModelPart upperTeeth;
	protected final ModelPart lowerTeeth;
	protected final ModelPart upperMouthOverlay;
	protected final ModelPart lowerMouthOverlay;

	public MimicModel(ModelPart root) {
		upperTeeth = root.getChild("upper_teeth");
		lowerTeeth = root.getChild("lower_teeth");
		upperMouthOverlay = root.getChild("upper_mouth_overlay");
		lowerMouthOverlay = root.getChild("lower_mouth_overlay");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder upperTeeth = ModelPartBuilder.create().uv(0, 0).cuboid(-6, 0, -13, 12, 3, 12);
		ModelPartBuilder lowerTeeth = ModelPartBuilder.create().uv(0, 15).cuboid(-6, -4, -13, 12, 3, 12);
		ModelPartBuilder upperMouthOverlay = ModelPartBuilder.create().uv(24, 0).cuboid(-6, 0, -13, 12, 0, 12, new Dilation( 0.02F));
		ModelPartBuilder lowerMouthOverlay = ModelPartBuilder.create().uv(36, 15).cuboid(-6, -1, -13, 12, 0, 12, new Dilation( 0.02F));

		root.addChild("upper_teeth", upperTeeth, ModelTransform.pivot(0, 15, 7));
		root.addChild("lower_teeth", lowerTeeth, ModelTransform.pivot(0, 15, 7));
		root.addChild("upper_mouth_overlay", upperMouthOverlay, ModelTransform.pivot(0, 15, 7));
		root.addChild("lower_mouth_overlay", lowerMouthOverlay, ModelTransform.pivot(0, 15, 7));

		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void setAngles(MimicEntity mimic, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void animateModel(MimicEntity mimic, float limbAngle, float limbDistance, float tickDelta) {
		if (mimic.ticksInAir > 0) {
			upperTeeth.pitch = upperMouthOverlay.pitch = Math.max(-60, (mimic.ticksInAir - 1 + tickDelta) * -6) * 0.0174533F;
			lowerTeeth.pitch = lowerMouthOverlay.pitch = Math.min(30, (mimic.ticksInAir - 1 + tickDelta) * 3) * 0.0174533F;
		} else {
			upperTeeth.pitch = upperMouthOverlay.pitch = 0;
			lowerTeeth.pitch = lowerMouthOverlay.pitch = 0;
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		upperTeeth.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		lowerTeeth.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		upperMouthOverlay.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		lowerMouthOverlay.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}
