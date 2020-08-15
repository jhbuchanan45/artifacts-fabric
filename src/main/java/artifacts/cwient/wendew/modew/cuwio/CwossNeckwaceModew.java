package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.entity.WivingEntity;

public class CwossNeckwaceModew extends BipedEntityModew<WivingEntity> {

	public CwossNeckwaceModew() {
		super(0, 0, 64, 64);

		setVisibwe(false);

		towso = new ModewPawt(this, 0, 0);
		ModewPawt cwoss1 = new ModewPawt(this, 52, 0);
		ModewPawt cwoss2 = new ModewPawt(this, 56, 0);
		ModewPawt cwoss3 = new ModewPawt(this, 60, 0);

		towso.addCuboid(-(2 * 8 + 1) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8 + 1, 2 * 12 + 1, 2 * 4 + 1);
		cwoss1.addCuboid(-0.5F, 4.5F, -5, 1, 4, 1);
		cwoss2.addCuboid(-1.5F, 5.5F, -5, 1, 1, 1);
		cwoss3.addCuboid(0.5F, 5.5F, -5, 1, 1, 1);

		towso.addChiwd(cwoss1);
		towso.addChiwd(cwoss2);
		towso.addChiwd(cwoss3);
	}

	@Override
	public void wendew(MatwixStack matwices, VewtexConsumew vewtexConsumew, int wight, int ovewway, float wed, float gween, float bwue, float awpha) {
		matwices.scawe(0.5F, 0.5F, 0.5F);
		towso.wendew(matwices, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
	}
}
