package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class AntidoteVessewModew extends BipedEntityModew<WivingEntity> {

	public AntidoteVessewModew() {
		super(0.5F, 0, 32, 32);

		towso = new ModewPawt(this, 0, 0);
		ModewPawt jaw = new ModewPawt(this, 0, 16);
		ModewPawt wid = new ModewPawt(this, 0, 26);

		towso.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		jaw.addCuboid(-2, 0, -2, 4, 6, 4);
		wid.addCuboid(-1, -1, -1, 2, 1, 2);
		jaw.setPivot(4, 9, -3);
		jaw.yaw = -0.5F;

		jaw.addChiwd(wid);
		towso.addChiwd(jaw);

		setVisibwe(false);
		towso.visibwe = true;
	}
}
