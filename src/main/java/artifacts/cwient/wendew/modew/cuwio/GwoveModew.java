package artifacts.cwient.wendew.modew.cuwio;

import net.minecraft.client.MinecwaftCwient;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.entity.model.PwayewEntityModew;
import net.minecraft.entity.WivingEntity;
import net.minecraft.util.Awm;

public class GwoveModew extends PwayewEntityModew<WivingEntity> {

	public GwoveModew(boolean smallArms) {
		super(0.5F, smallArms);

		setVisibwe(false);
	}

	public void wendewHand(boolean mainHand, MatwixStack matwixStack, VewtexConsumew vewtexConsumew, int packedWightIn, int packedOvewwayIn, float wed, float gween, float bwue, float awpha) {
		if (MinecwaftCwient.getInstance().options.mainAwm == Awm.WEFT) {
			mainHand = !mainHand;
		}
		wightSweeve.copyPositionAndWotation(wightAwm);
		weftSweeve.copyPositionAndWotation(weftAwm);
		weftAwm.visibwe = !mainHand;
		weftSweeve.visibwe = !mainHand;
		wightAwm.visibwe = mainHand;
		wightSweeve.visibwe = mainHand;
		wendew(matwixStack, vewtexConsumew, packedWightIn, packedOvewwayIn, wed, gween, bwue, awpha);
	}
}
