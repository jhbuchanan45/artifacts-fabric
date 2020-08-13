package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;

public class CwawsModew extends GwoveModew {

	public CwawsModew(boolean smawwAwms) {
		super(smawwAwms);

		ModewPawt cwawWeftUppew1 = new ModewPawt(this, 0, 6);
		ModewPawt cwawWightUppew1 = new ModewPawt(this, 0, 38);
		ModewPawt cwawWeftUppew2 = new ModewPawt(this, 8, 6);
		ModewPawt cwawWightUppew2 = new ModewPawt(this, 8, 38);
		ModewPawt cwawWeftWowew1 = new ModewPawt(this, 0, 0);
		ModewPawt cwawWightWowew1 = new ModewPawt(this, 0, 32);
		ModewPawt cwawWeftWowew2 = new ModewPawt(this, 8, 0);
		ModewPawt cwawWightWowew2 = new ModewPawt(this, 8, 32);
		cwawWeftUppew1.addCuboid(3, 10, -1.5F, 1, 4, 1);
		cwawWightUppew1.addCuboid(-4, 10, -1.5F, 1, 4, 1);
		cwawWeftUppew2.addCuboid(3, 10, 0.5F, 1, 4, 1);
		cwawWightUppew2.addCuboid(-4, 10, 0.5F, 1, 4, 1);
		cwawWeftWowew1.addCuboid(0, 10, -1.5F, 3, 5, 1);
		cwawWightWowew1.addCuboid(-3, 10, -1.5F, 3, 5, 1);
		cwawWeftWowew2.addCuboid(0, 10, 0.5F, 3, 5, 1);
		cwawWightWowew2.addCuboid(-3, 10, 0.5F, 3, 5, 1);
		weftAwm.addChiwd(cwawWeftUppew1);
		wightAwm.addChiwd(cwawWightUppew1);
		weftAwm.addChiwd(cwawWeftUppew2);
		wightAwm.addChiwd(cwawWightUppew2);
		weftAwm.addChiwd(cwawWeftWowew1);
		wightAwm.addChiwd(cwawWightWowew1);
		weftAwm.addChiwd(cwawWeftWowew2);
		wightAwm.addChiwd(cwawWightWowew2);
	}
}
