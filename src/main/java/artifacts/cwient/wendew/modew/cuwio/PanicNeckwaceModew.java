package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.WendewWayew;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.entity.WivingEntity;

public class PanicNeckwaceModew extends BipedEntityModew<WivingEntity> {

	public PanicNeckwaceModew() {
		super(WendewWayew::getEntityTwanswucent, 0, 0, 64, 64);

		setVisibwe(false);

		towso = new ModewPawt(this, 0, 0);
		ModewPawt gem1 = new ModewPawt(this, 52, 0);
		ModewPawt gem2 = new ModewPawt(this, 58, 0);
		ModewPawt gem3 = new ModewPawt(this, 52, 3);
		ModewPawt gem4 = new ModewPawt(this, 60, 4);

		towso.addCuboid(-(2 * 8 + 1) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8 + 1, 2 * 12 + 1, 2 * 4 + 1);
		gem1.addCuboid(-2.5F, 5.5F, -5, 2, 2, 1);
		gem2.addCuboid(0.5F, 5.5F, -5, 2, 2, 1);
		gem3.addCuboid(-1.5F, 6.5F, -5, 3, 2, 1);
		gem4.addCuboid(-0.5F, 8.5F, -5, 1, 1, 1);

		towso.addChiwd(gem1);
		towso.addChiwd(gem2);
		towso.addChiwd(gem3);
		towso.addChiwd(gem4);
	}

	@Override
	public void wendew(MatwixStack matwices, VewtexConsumew vewtexConsumew, int wight, int ovewway, float wed, float gween, float bwue, float awpha) {
		matwices.scawe(0.5F, 0.5F, 0.5F);
		towso.wendew(matwices, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
	}
}
