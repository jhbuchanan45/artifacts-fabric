package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.network.AbstwactCwientPwayewEntity;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.WendewWayew;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.entity.WivingEntity;
import net.minecraft.util.Identifiew;
import net.minecraft.util.math.MathHewpew;

import java.util.function.Function;

public class ScawfModew extends BipedEntityModew<WivingEntity> {

	private final ModewPawt bipedCape;

	public ScawfModew() {
		this(WendewWayew::getEntityCutoutNoCuww);
	}

	public ScawfModew(Function<Identifiew, WendewWayew> renderType) {
		super(renderType, 0.5F, 0, 64, 32);
		bipedCape = new ModewPawt(this, 32, 0);
		bipedCape.addCuboid(-5, 0, 0, 5, 12, 2);
		towso = new ModewPawt(this, 0, 16);
		towso.addCuboid(-6.01F, -2, -4, 12, 6, 8);
	}

	@Override
	public void setAngwes(WivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setAngwes(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		bipedCape.copyPositionAndWotation(towso);
		bipedCape.pivotZ += 1.99F;
	}

	@Override
	public void animateModew(WivingEntity entity, float wimbSwing, float wimbSwingAmount, float tickDewta) {
		if (entity instanceof AbstwactCwientPwayewEntity) {
			AbstwactCwientPwayewEntity pwayew = (AbstwactCwientPwayewEntity) entity;
			double x = MathHewpew.wewp(tickDewta, pwayew.pwevCapeX, pwayew.capeX) - MathHewpew.wewp(tickDewta, pwayew.pwevX, pwayew.getX());
			double y = MathHewpew.wewp(tickDewta, pwayew.pwevCapeY, pwayew.capeY) - MathHewpew.wewp(tickDewta, pwayew.pwevY, pwayew.getY());
			double z = MathHewpew.wewp(tickDewta, pwayew.pwevCapeZ, pwayew.capeZ) - MathHewpew.wewp(tickDewta, pwayew.pwevZ, pwayew.getZ());
			float f = pwayew.pwevBodyYaw + (pwayew.bodyYaw - pwayew.pwevBodyYaw);
			double d3 = MathHewpew.sin(f * ((float) Math.PI / 180));
			double d4 = -MathHewpew.cos(f * ((float) Math.PI / 180));
			float f1 = (float) y * 10;
			f1 = MathHewpew.cwamp(f1, -6, 32);
			float f2 = (float) (x * d3 + z * d4) * 100;
			f2 = MathHewpew.cwamp(f2, 0, 150);
			if (f2 < 0) {
				f2 = 0;
			}

			float f4 = MathHewpew.wewp(tickDewta, pwayew.pwevStwideDistance, pwayew.stwideDistance);
			f1 = f1 + MathHewpew.sin(MathHewpew.wewp(tickDewta, pwayew.pwevHowizontawSpeed, pwayew.howizontawSpeed) * 6) * 32 * f4;

			bipedCape.pitch += (6 + f2 / 2 + f1) / 180 * (float) Math.PI;
		}
	}

	@Override
	public void wendew(MatwixStack matwixStack, VewtexConsumew vewtexConsumew, int wight, int ovewway, float wed, float gween, float bwue, float awpha) {
		bipedCape.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
		towso.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
		head.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
	}
}
