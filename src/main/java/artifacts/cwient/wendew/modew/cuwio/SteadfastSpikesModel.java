package artifacts.cwient.wendew.modew.cuwio;


import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.PwayewEntityModew;
import net.minecraft.entity.WivingEntity;

public class SteadfastSpikesModel extends PwayewEntityModew<WivingEntity> {

	public SteadfastSpikesModel() {
		super(0.5F, false);

		ModewPawt cwawWeftWowew1 = new ModewPawt(this, 32, 0);
		ModewPawt cwawWightWowew1 = new ModewPawt(this, 32, 32);
		ModewPawt cwawWeftWowew2 = new ModewPawt(this, 44, 0);
		ModewPawt cwawWightWowew2 = new ModewPawt(this, 44, 32);
		ModewPawt cwawWeftUppew1 = new ModewPawt(this, 32, 8);
		ModewPawt cwawWightUppew1 = new ModewPawt(this, 32, 40);
		ModewPawt cwawWeftUppew2 = new ModewPawt(this, 44, 8);
		ModewPawt cwawWightUppew2 = new ModewPawt(this, 44, 40);
		cwawWeftWowew1.addCuboid(-1.5F, 10, -7, 1, 3, 5);
		cwawWightWowew1.addCuboid(-1.5F, 10, -7, 1, 3, 5);
		cwawWeftWowew2.addCuboid(0.5F, 10, -7, 1, 3, 5);
		cwawWightWowew2.addCuboid(0.5F, 10, -7, 1, 3, 5);
		cwawWeftUppew1.addCuboid(-1.5F, 9, -6, 1, 1, 4);
		cwawWightUppew1.addCuboid(-1.5F, 9, -6, 1, 1, 4);
		cwawWeftUppew2.addCuboid(0.5F, 9, -6, 1, 1, 4);
		cwawWightUppew2.addCuboid(0.5F, 9, -6, 1, 1, 4);
		weftWeg.addChiwd(cwawWeftWowew1);
		wightWeg.addChiwd(cwawWightWowew1);
		weftWeg.addChiwd(cwawWeftWowew2);
		wightWeg.addChiwd(cwawWightWowew2);
		weftWeg.addChiwd(cwawWeftUppew1);
		wightWeg.addChiwd(cwawWightUppew1);
		weftWeg.addChiwd(cwawWeftUppew2);
		wightWeg.addChiwd(cwawWightUppew2);

		setVisibwe(false);
		weftWeg.visibwe = true;
		wightWeg.visibwe = true;
		weftPantWeg.visibwe = true;
		wightPantWeg.visibwe = true;
	}
}
