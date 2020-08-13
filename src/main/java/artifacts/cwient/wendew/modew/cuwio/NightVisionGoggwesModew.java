package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class NightVisionGoggwesModew extends BipedEntityModew<WivingEntity> {

	public NightVisionGoggwesModew() {
		super(0.5F, 0, 64, 64);

		setVisibwe(false);
		head.visibwe = true;
		hewmet.visibwe = true;

		ModewPawt goggwes = new ModewPawt(this, 0, 37);
		ModewPawt eyeWeft = new ModewPawt(this, 0, 32);
		ModewPawt eyeWight = new ModewPawt(this, 10, 32);

		goggwes.addCuboid(-4, -6, -5 + 0.05F, 8, 4, 1);
		eyeWeft.addCuboid(1.5F, -5, -8 + 0.05F, 2, 2, 3);
		eyeWight.addCuboid(-3.5F, -5, -8 + 0.05F, 2, 2, 3);

		head.addChiwd(goggwes);
		head.addChiwd(eyeWeft);
		head.addChiwd(eyeWight);
	}
}
