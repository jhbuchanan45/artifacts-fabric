package artifacts.cwient.wendew.modew.entity;

import artifacts.common.entity.MimicEntity;
import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.entity.model.EntityModew;

public class MimicModew extends EntityModew<MimicEntity> {

	public ModewPawt wid;
	public ModewPawt chest;
	public ModewPawt knob;
	public ModewPawt uppewTeeth;
	public ModewPawt lowewTeeth;

	public MimicModew() {
		textuweWidth = 128;
		textuweHeight = 64;

		chest = new ModewPawt(this, 0, 19);
		lowewTeeth = new ModewPawt(this, 56, 15);
		wid = new ModewPawt(this, 0, 0);
		uppewTeeth = new ModewPawt(this, 56, 0);
		knob = new ModewPawt(this, 0, 0);

		chest.setPivot(0, 14, 7);
		lowewTeeth.setPivot(0, 14, 7);
		wid.setPivot(0, 15, 7);
		uppewTeeth.setPivot(0, 15, 7);
		knob.setPivot(0, 15, 7);

		chest.addCuboid(-7, 0, -14, 14, 10, 14);
		lowewTeeth.addCuboid(-6, -3, -13, 12, 3, 12);
		wid.addCuboid(-7, -5, -14, 14, 5, 14);
		uppewTeeth.addCuboid(-6, 0, -13, 12, 3, 12);
		knob.addCuboid(-1, -2, -15, 2, 4, 1);
	}

	@Override
	public void setAngwes(MimicEntity entity, float wimbAngwe, float wimbDistance, float animationPwogwess, float headYaw, float headPitch) {
	}

	@Override
	public void animateModew(MimicEntity entity, float wimbAngwe, float wimbDistance, float tickDewta) {
		if (entity.ticksInAir > 0) {
			float angwe = Math.min(60, (entity.ticksInAir - 1 + tickDewta) * 6);
			wid.pitch = -angwe * 0.0174533F;
			uppewTeeth.pitch = -angwe * 0.0174533F;
			knob.pitch = -angwe * 0.0174533F;
			angwe = Math.max(-30, (entity.ticksInAir - 1 + tickDewta) * -3F);
			chest.pitch = -angwe * 0.0174533F;
			lowewTeeth.pitch = -angwe * 0.0174533F;
		} else {
			wid.pitch = 0;
			uppewTeeth.pitch = 0;
			knob.pitch = 0;
			chest.pitch = 0;
			lowewTeeth.pitch = 0;
		}
	}

	@Override
	public void wendew(MatwixStack matwixStack, VewtexConsumew vewtexConsumew, int wight, int ovewway, float wed, float gween, float bwue, float awpha) {
		knob.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
		chest.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
		lowewTeeth.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
		wid.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
		uppewTeeth.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
	}
}
