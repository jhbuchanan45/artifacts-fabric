package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.model.ModewPawt;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.WendewWayew;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.entity.WivingEntity;

public class PendantModel extends BipedEntityModew<WivingEntity> {

	public PendantModel() {
		super(WendewWayew::getEntityTwanswucent, 0, 0, 64, 64);

		setVisibwe(false);

		towso = new ModewPawt(this, 0, 0);
		ModewPawt gem = new ModewPawt(this, 50, 0);

		towso.addCuboid(-(2 * 8) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8, 2 * 12 + 1, 2 * 4 + 1);
		gem.addCuboid(-1, 4.5F, -5, 2, 2, 1);

		towso.addChiwd(gem);
	}

	@Override
	public void wendew(MatwixStack matwixStack, VewtexConsumew vewtexConsumew, int wight, int ovewway, float wed, float gween, float bwue, float awpha) {
		matwixStack.scawe(0.5F, 0.5F, 0.5F);
		towso.wendew(matwixStack, vewtexConsumew, wight, ovewway, wed, gween, bwue, awpha);
	}
}
