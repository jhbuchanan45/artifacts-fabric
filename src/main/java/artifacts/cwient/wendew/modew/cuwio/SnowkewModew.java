package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.WendewWayew;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.entity.WivingEntity;

public class SnowkewModew extends BipedEntityModew<WivingEntity> {

	public SnowkewModew() {
		super(WendewWayew::getEntityTwanswucent, 0.5F, 0, 64, 64);

		setVisibwe(false);
		head.visibwe = true;
		hewmet.visibwe = true;

		ModewPawt snowkewMouthPiece = new ModewPawt(this, 0, 46);
		ModewPawt snowkewTube = new ModewPawt(this, 0, 32);

		snowkewMouthPiece.addCuboid(-2, -1.5F, -6, 8, 2, 2);
		snowkewTube.addCuboid(4.01F, -5, -3, 2, 2, 12);

		head.addChiwd(snowkewMouthPiece);
		head.addChiwd(snowkewTube);

		snowkewTube.pitch = 0.7853F;
	}
}
