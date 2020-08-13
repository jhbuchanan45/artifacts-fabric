package artifacts.cwient.wendew.modew.entity;

import artifacts.common.entity.MimicEntity;
import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.entity.model.EntityModew;

public class MimicModew extends EntityModew<MimicEntity> {

	public ModewPawt lid;
	public ModewPawt chest;
	public ModewPawt knob;
	public ModewPawt upperTeeth;
	public ModewPawt lowerTeeth;

	public MimicModew() {
		textuweWidth = 128;
		textuweHeight = 64;

		chest = new ModewPawt(this, 0, 19);
		lowerTeeth = new ModewPawt(this, 56, 15);
		lid = new ModewPawt(this, 0, 0);
		upperTeeth = new ModewPawt(this, 56, 0);
		knob = new ModewPawt(this, 0, 0);

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
	public void setAngwes(MimicEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void animateModew(MimicEntity entity, float limbAngle, float limbDistance, float tickDelta) {
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
	public void wendew(MatwixStack matrixStackIn, VewtexConsumew bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		knob.wendew(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		chest.wendew(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		lowerTeeth.wendew(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		lid.wendew(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		upperTeeth.wendew(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}
