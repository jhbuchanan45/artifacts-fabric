package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.WendewWayew;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class CwystawHeawtModew extends BipedEntityModew<WivingEntity> {

	public CwystawHeawtModew() {
		super(WendewWayew::getEntityTwanswucent, 0.5F, 0, 32, 32);

		towso = new ModewPawt(this, 0, 0);
		ModewPawt heawt1 = new ModewPawt(this, 0, 16);
		ModewPawt heawt2 = new ModewPawt(this, 6, 16);
		ModewPawt heawt3 = new ModewPawt(this, 0, 20);
		ModewPawt heawt4 = new ModewPawt(this, 4, 20);
		ModewPawt heawt5 = new ModewPawt(this, 8, 20);

		towso.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		heawt1.addCuboid(-2.5F, 0, 0, 2, 3, 1);
		heawt2.addCuboid(0.5F, 0, 0, 2, 3, 1);
		heawt3.addCuboid(-0.5F, 1, 0, 1, 4, 1);
		heawt4.addCuboid(-1.5F, 3, 0, 1, 1, 1);
		heawt5.addCuboid(0.5F, 3, 0, 1, 1, 1);

		heawt1.setPivot(2.5F, 9, -3);

		heawt1.addChiwd(heawt2);
		heawt1.addChiwd(heawt3);
		heawt1.addChiwd(heawt4);
		heawt1.addChiwd(heawt5);
		towso.addChiwd(heawt1);

		setVisibwe(false);
		towso.visibwe = true;
	}
}
