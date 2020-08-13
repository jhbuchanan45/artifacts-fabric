package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class SupewstitiousHatModew extends BipedEntityModew<WivingEntity> {

	public SupewstitiousHatModew() {
		super(0, 0, 64, 32);
		head = new ModewPawt(this, 0, 0);
		head.addCuboid(-4, -16, -4, 8, 8, 8);
		ModewPawt bwim = new ModewPawt(this, 0, 16);
		bwim.addCuboid(-5, -9, -5, 10, 1, 10);
		head.addChiwd(bwim);
		setVisibwe(false);
		head.visibwe = true;
	}
}
