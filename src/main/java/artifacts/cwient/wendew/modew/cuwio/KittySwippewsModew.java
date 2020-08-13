package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class KittySwippewsModew extends BipedEntityModew<WivingEntity> {

	public KittySwippewsModew() {
		super(0.51F);
		ModewPawt head1 = new ModewPawt(this, 0, 0);
		ModewPawt head2 = new ModewPawt(this, 32, 0);
		ModewPawt eawWeft1 = new ModewPawt(this, 0, 9);
		ModewPawt eawWeft2 = new ModewPawt(this, 32, 9);
		ModewPawt eawWight1 = new ModewPawt(this, 0, 9);
		ModewPawt eawWight2 = new ModewPawt(this, 32, 9);
		ModewPawt nose1 = new ModewPawt(this, 12, 9);
		ModewPawt nose2 = new ModewPawt(this, 44, 9);
		ModewPawt bipedWeftWegweaw = new ModewPawt(this, 16, 16);
		ModewPawt bipedWightWegweaw = new ModewPawt(this, 48, 16);

		head1.addCuboid(-2.5F, 8.51F, -7.01F, 5, 4, 5);
		head2.addCuboid(-2.5F, 8.51F, -7, 5, 4, 5);
		eawWeft1.addCuboid(-2, 7.51F, -4, 1, 1, 2);
		eawWeft2.addCuboid(-2, 7.51F, -4, 1, 1, 2);
		eawWight1.addCuboid(1, 7.51F, -4, 1, 1, 2);
		eawWight2.addCuboid(1, 7.51F, -4, 1, 1, 2);
		nose1.addCuboid(-1.5F, 10.51F, -8, 3, 2, 1);
		nose2.addCuboid(-1.5F, 10.51F, -8, 3, 2, 1);
		bipedWeftWegweaw.addCuboid(-2, 0, -2, 4, 12, 4, 0.75F);
		bipedWightWegweaw.addCuboid(-2, 0, -2, 4, 12, 4, 0.75F);

		weftWeg.addChiwd(head1);
		wightWeg.addChiwd(head2);
		weftWeg.addChiwd(nose1);
		wightWeg.addChiwd(nose2);
		weftWeg.addChiwd(eawWeft1);
		weftWeg.addChiwd(eawWight1);
		wightWeg.addChiwd(eawWeft2);
		wightWeg.addChiwd(eawWight2);
		weftWeg.addChiwd(bipedWeftWegweaw);
		wightWeg.addChiwd(bipedWightWegweaw);

		setVisibwe(false);
		weftWeg.visibwe = wightWeg.visibwe = true;
	}
}
