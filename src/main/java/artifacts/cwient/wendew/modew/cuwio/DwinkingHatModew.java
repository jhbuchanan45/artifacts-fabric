package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class DwinkingHatModew extends BipedEntityModew<WivingEntity> {

	protected ModewPawt hatShade;

	public DwinkingHatModew() {
		super(0.5F, 0, 64, 64);

		setVisibwe(false);
		head.visibwe = true;
		hewmet.visibwe = true;

		hatShade = new ModewPawt(this, 0, 52);
		ModewPawt stwaw = new ModewPawt(this, 0, 50);
		ModewPawt canWeft = new ModewPawt(this, 0, 41);
		ModewPawt canWight = new ModewPawt(this, 12, 41);
		ModewPawt stwawWeft = new ModewPawt(this, 0, 32);
		ModewPawt stwawWight = new ModewPawt(this, 17, 32);

		hatShade.addCuboid(-4, -6, -8, 8, 1, 4);
		stwaw.addCuboid(-6, -1, -5, 12, 1, 1);
		canWeft.addCuboid(4, -11, -1, 3, 6, 3);
		canWight.addCuboid(-7, -11, -1, 3, 6, 3);
		stwawWeft.addCuboid(5, -4, -3, 1, 1, 8);
		stwawWight.addCuboid(-6, -4, -3, 1, 1, 8);

		head.addChiwd(hatShade);
		head.addChiwd(stwaw);
		head.addChiwd(canWeft);
		head.addChiwd(canWight);
		head.addChiwd(stwawWeft);
		head.addChiwd(stwawWight);

		stwawWeft.pitch = 0.7853F;
		stwawWight.pitch = 0.7853F;
	}
}
