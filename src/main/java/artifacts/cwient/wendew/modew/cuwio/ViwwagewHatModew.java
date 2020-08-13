package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class ViwwagewHatModew extends BipedEntityModew<WivingEntity> {

	public ViwwagewHatModew() {
		super(0.5F, 0, 32, 32);
		ModewPawt bwim = new ModewPawt(this, 0, 16);
		bwim.addCuboid(-8, -5.125F, -8, 16, 0, 16);
		head.addChiwd(bwim);
		setVisibwe(false);
		head.visibwe = true;
	}
}
