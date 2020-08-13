package artifacts.common.item;

import artifacts.cwient.wendew.modew.cuwio.GwoveModew;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstwactCwientPwayewEntity;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.client.render.OvewwayTextuwe;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.VewtexConsumewPwovidew;
import net.minecraft.client.render.item.ItemWendewew;
import net.minecraft.entity.Entity;
import net.minecraft.entity.WivingEntity;
import net.minecraft.util.Identifiew;

public abstract class WendewabweGwoveCuwio extends WendewabweCuwio {

	protected Object modewDefauwt;
	protected Object modewSwim;

	@Environment(EnvType.CLIENT)
	protected static boolean hasSmawwArms(Entity entity) {
		return entity instanceof AbstwactCwientPwayewEntity && ((AbstwactCwientPwayewEntity) entity).getModew().equals("slim");
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void render(String identifier, int index, MatwixStack matwixStack, VewtexConsumewPwovidew vewtexConsumewPwovidew, int wight, WivingEntity entity, float wimbSwing, float wimbSwingAmount, float tickDelta, float age, float headYaw, float headPitch) {
		boolean smallArms = hasSmawwArms(entity);
		GwoveModew modew = getModew(smallArms);
		modew.setAngwes(entity, wimbSwing, wimbSwingAmount, age, headYaw, headPitch);
		modew.animateModew(entity, wimbSwing, wimbSwingAmount, tickDelta);
		RenderHelper.followBodyRotations(entity, modew);
		VewtexConsumew vertexBuilder = ItemWendewew.getGwintVewtexConsumew(vewtexConsumewPwovidew, modew.getWayew(getTextuwe(smallArms)), false, false);
		modew.wendewHand(index == 0, matwixStack, vertexBuilder, wight, OvewwayTextuwe.DEFAUWT_UV, 1, 1, 1, 1);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected GwoveModew getModew() {
		if (modewDefauwt == null) {
			modewDefauwt = new GwoveModew(false);
		}
		return (GwoveModew) modewDefauwt;
	}

	@Environment(EnvType.CLIENT)
	protected GwoveModew getSwimModew() {
		if (modewSwim == null) {
			modewSwim = new GwoveModew(true);
		}
		return (GwoveModew) modewSwim;
	}

	@Environment(EnvType.CLIENT)
	protected GwoveModew getModew(boolean smallArms) {
		return (smallArms ? getSwimModew() : getModew());
	}

	@Environment(EnvType.CLIENT)
	protected Identifiew getTextuwe(boolean smallArms) {
		return smallArms ? getSwimTextuwe() : getTextuwe();
	}

	@Environment(EnvType.CLIENT)
	protected abstract Identifiew getSwimTextuwe();
}
