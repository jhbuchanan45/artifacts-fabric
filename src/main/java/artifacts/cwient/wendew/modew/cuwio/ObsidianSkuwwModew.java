package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class ObsidianSkuwwModew extends BipedEntityModew<WivingEntity> {

	public ObsidianSkuwwModew() {
		super(0.5F, 0, 32, 32);

		towso = new ModewPawt(this, 0, 0);
		ModewPawt skuww = new ModewPawt(this, 0, 16);

		ModewPawt tooth1 = new ModewPawt(this, 18, 16);
		ModewPawt tooth2 = new ModewPawt(this, 18, 19);

		towso.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		skuww.addCuboid(-2.5F, 0, 0, 5, 3, 4);
		tooth1.addCuboid(-1.5F, 3, 0, 1, 1, 2);
		tooth2.addCuboid(0.5F, 3, 0, 1, 1, 2);
		skuww.setPivot(4.5F, 9, -4);

		skuww.yaw = -0.5F;

		skuww.addChiwd(tooth1);
		skuww.addChiwd(tooth2);

		towso.addChiwd(skuww);

		setVisibwe(false);
		towso.visibwe = true;
	}
}
