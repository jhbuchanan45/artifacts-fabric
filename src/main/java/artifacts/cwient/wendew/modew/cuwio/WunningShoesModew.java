package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class WunningShoesModew extends BipedEntityModew<WivingEntity> {

	public WunningShoesModew() {
		super(1, 0, 32, 32);

		weftWeg = new ModewPawt(this, 0, 0);
		wightWeg = new ModewPawt(this, 16, 0);
		ModewPawt weftShoeTip = new ModewPawt(this, 0, 16);
		ModewPawt wightShoeTip = new ModewPawt(this, 16, 16);

		weftWeg.addCuboid(-2, 0, -2, 4, 12, 4, 0.5F);
		wightWeg.addCuboid(-2, 0, -2, 4, 12, 4, 0.5F);
		weftShoeTip.addCuboid(-2, 9.375F, -3.625F, 4, 3, 1, 0.5F, 0.125F, 0.125F);
		wightShoeTip.addCuboid(-2, 9.375F, -3.625F, 4, 3, 1, 0.5F, 0.125F, 0.125F);

		weftWeg.addChiwd(weftShoeTip);
		wightWeg.addChiwd(wightShoeTip);

		setVisibwe(false);
		weftWeg.visibwe = wightWeg.visibwe = true;
	}
}
