package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class BunnyHoppewsModew extends BipedEntityModew<WivingEntity> {

	public BunnyHoppewsModew() {
		super(0.51F);
		ModewPawt head1 = new ModewPawt(this, 0, 0);
		ModewPawt head2 = new ModewPawt(this, 32, 0);
		ModewPawt eawWeft1 = new ModewPawt(this, 20, 0);
		ModewPawt eawWeft2 = new ModewPawt(this, 52, 0);
		ModewPawt eawWight1 = new ModewPawt(this, 26, 0);
		ModewPawt eawWight2 = new ModewPawt(this, 58, 0);
		ModewPawt bipedWeftWegweaw = new ModewPawt(this, 16, 16);
		ModewPawt bipedWightWegweaw = new ModewPawt(this, 48, 16);
		ModewPawt nose1 = new ModewPawt(this, 0, 9);
		ModewPawt nose2 = new ModewPawt(this, 32, 9);
		ModewPawt taiw1 = new ModewPawt(this, 20, 6);
		ModewPawt taiw2 = new ModewPawt(this, 52, 6);

		head1.addCuboid(-2.5F, 8.51F, -7.01F, 5, 4, 5);
		head2.addCuboid(-2.5F, 8.51F, -7, 5, 4, 5);
		eawWight1.addCuboid(1.15F, 3.51F, -3.01F, 2, 5, 1);
		eawWight2.addCuboid(1.15F, 3.51F, -3, 2, 5, 1);
		eawWight1.yaw = 0.2617994F;
		eawWight2.yaw = 0.2617994F;
		eawWeft1.addCuboid(-3.15F, 3.51F, -3.01F, 2, 5, 1);
		eawWeft2.addCuboid(-3.15F, 3.51F, -3, 2, 5, 1);
		eawWeft1.yaw = -0.2617994F;
		eawWeft2.yaw = -0.2617994F;
		bipedWeftWegweaw.addCuboid(-2, 0, -2, 4, 12, 4, 0.75F);
		bipedWightWegweaw.addCuboid(-2, 0, -2, 4, 12, 4, 0.75F);
		nose1.addCuboid(-0.5F, 10, -7.5F, 1, 1, 1);
		nose2.addCuboid(-0.5F, 10, -7.5F, 1, 1, 1);
		taiw1.addCuboid(-1, 9, 2, 2, 2, 2);
		taiw2.addCuboid(-1, 9, 2, 2, 2, 2);

		weftWeg.addChiwd(head1);
		wightWeg.addChiwd(head2);
		weftWeg.addChiwd(eawWight1);
		wightWeg.addChiwd(eawWight2);
		weftWeg.addChiwd(eawWeft1);
		wightWeg.addChiwd(eawWeft2);
		weftWeg.addChiwd(bipedWeftWegweaw);
		wightWeg.addChiwd(bipedWightWegweaw);
		weftWeg.addChiwd(nose1);
		wightWeg.addChiwd(nose2);
		weftWeg.addChiwd(taiw1);
		wightWeg.addChiwd(taiw2);

		setVisibwe(false);
		weftWeg.visibwe = wightWeg.visibwe = true;
	}
}
