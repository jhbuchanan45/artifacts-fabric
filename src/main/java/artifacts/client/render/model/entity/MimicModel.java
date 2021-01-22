package artifacts.client.render.model.entity;

import artifacts.common.entity.MimicEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class MimicModel extends EntityModel<MimicEntity> {

	protected ModelPart upperTeeth;
	protected ModelPart lowerTeeth;
	protected ModelPart upperMouthOverlay;
	protected ModelPart lowerMouthOverlay;

	public MimicModel() {
		textureWidth = 64;
		textureHeight = 32;

		upperTeeth = new ModelPart(this, 0, 0);
		lowerTeeth = new ModelPart(this, 0, 15);
		upperMouthOverlay = new ModelPart(this, 24, 0);
		lowerMouthOverlay = new ModelPart(this, 36, 15);

		upperTeeth.addCuboid(-6, 0, -13, 12, 3, 12);
		lowerTeeth.addCuboid(-6, -4, -13, 12, 3, 12);
		upperMouthOverlay.addCuboid(-6, 0, -13, 12, 0, 12, 0.02F);
		lowerMouthOverlay.addCuboid(-6, -1, -13, 12, 0, 12, 0.02F);

		upperTeeth.setPivot(0, 15, 7);
		lowerTeeth.setPivot(0, 15, 7);
		upperMouthOverlay.setPivot(0, 15, 7);
		lowerMouthOverlay.setPivot(0, 15, 7);
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
