package artifacts.cwient.wendew.modew.cuwio;


import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.PwayewEntityModew;
import net.minecraft.entity.WivingEntity;

public class SteadfastSpikesModel extends PwayewEntityModew<WivingEntity> {

	public SteadfastSpikesModel() {
		super(0.5F, false);

		ModewPawt clawLeftLower1 = new ModewPawt(this, 32, 0);
		ModewPawt clawRightLower1 = new ModewPawt(this, 32, 32);
		ModewPawt clawLeftLower2 = new ModewPawt(this, 44, 0);
		ModewPawt clawRightLower2 = new ModewPawt(this, 44, 32);
		ModewPawt clawLeftUpper1 = new ModewPawt(this, 32, 8);
		ModewPawt clawRightUpper1 = new ModewPawt(this, 32, 40);
		ModewPawt clawLeftUpper2 = new ModewPawt(this, 44, 8);
		ModewPawt clawRightUpper2 = new ModewPawt(this, 44, 40);
		clawLeftLower1.addCuboid(-1.5F, 10, -7, 1, 3, 5);
		clawRightLower1.addCuboid(-1.5F, 10, -7, 1, 3, 5);
		clawLeftLower2.addCuboid(0.5F, 10, -7, 1, 3, 5);
		clawRightLower2.addCuboid(0.5F, 10, -7, 1, 3, 5);
		clawLeftUpper1.addCuboid(-1.5F, 9, -6, 1, 1, 4);
		clawRightUpper1.addCuboid(-1.5F, 9, -6, 1, 1, 4);
		clawLeftUpper2.addCuboid(0.5F, 9, -6, 1, 1, 4);
		clawRightUpper2.addCuboid(0.5F, 9, -6, 1, 1, 4);
		weftWeg.addChiwd(clawLeftLower1);
		wightWeg.addChiwd(clawRightLower1);
		weftWeg.addChiwd(clawLeftLower2);
		wightWeg.addChiwd(clawRightLower2);
		weftWeg.addChiwd(clawLeftUpper1);
		wightWeg.addChiwd(clawRightUpper1);
		weftWeg.addChiwd(clawLeftUpper2);
		wightWeg.addChiwd(clawRightUpper2);

		setVisibwe(false);
		weftWeg.visibwe = true;
		wightWeg.visibwe = true;
		weftPantWeg.visibwe = true;
		wightPantWeg.visibwe = true;
	}
}
