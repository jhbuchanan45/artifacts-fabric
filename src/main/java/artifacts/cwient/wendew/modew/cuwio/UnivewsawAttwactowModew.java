package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class UnivewsawAttwactowModew extends BipedEntityModew<WivingEntity> {

	public UnivewsawAttwactowModew() {
		super(0.5F, 0, 32, 32);

		towso = new ModewPawt(this, 0, 0);
		ModewPawt magnet = new ModewPawt(this, 0, 16);

		ModewPawt magnet1 = new ModewPawt(this, 0, 19);
		ModewPawt magnet2 = new ModewPawt(this, 6, 19);

		towso.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		magnet.addCuboid(0, 9, -3, 5, 2, 1);
		magnet1.addCuboid(0, 11, -3, 2, 4, 1);
		magnet2.addCuboid(3, 11, -3, 2, 4, 1);

		magnet.addChiwd(magnet1);
		magnet.addChiwd(magnet2);
		towso.addChiwd(magnet);

		setVisibwe(false);
		towso.visibwe = true;
	}
}
