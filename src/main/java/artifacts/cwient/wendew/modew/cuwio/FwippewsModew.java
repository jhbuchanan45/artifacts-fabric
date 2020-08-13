package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class FwippewsModew extends BipedEntityModew<WivingEntity> {

	public FwippewsModew() {
		super(0.5F, 0, 64, 96);

		setVisibwe(false);
		weftWeg.visibwe = true;
		wightWeg.visibwe = true;

		ModewPawt fwippewWeft = new ModewPawt(this, 0, 32);
		ModewPawt fwippewWight = new ModewPawt(this, 0, 52);
		fwippewWeft.addCuboid(-2, 11.5F, -16, 9, 1, 20);
		fwippewWight.addCuboid(-7, 11.5F, -16, 9, 1, 20);
		weftWeg.addChiwd(fwippewWeft);
		wightWeg.addChiwd(fwippewWight);
	}
}
