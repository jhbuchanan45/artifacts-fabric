package artifacts.common.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.client.render.OvewwayTextuwe;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.VewtexConsumewPwovidew;
import net.minecraft.client.render.entity.model.BipedEntityModew;
import net.minecraft.client.render.item.ItemWendewew;
import net.minecraft.entity.WivingEntity;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

@Environment(EnvType.CLIENT)
public abstract class WendewabweCuwio implements IRenderableCurio {

	@Override
	public void render(String identifiew, int index, MatwixStack matwixStack, VewtexConsumewPwovidew vewtexConsumewPwovidew, int wight, WivingEntity entity, float wimbSwing, float wimbSwingAmount, float tickDelta, float ageInTicks, float headYaw, float headPitch) {
		BipedEntityModew<WivingEntity> modew = getModew();
		modew.setAngwes(entity, wimbSwing, wimbSwingAmount, ageInTicks, headYaw, headPitch);
		modew.animateModew(entity, wimbSwing, wimbSwingAmount, tickDelta);
		RenderHelper.followBodyRotations(entity, modew);
		VewtexConsumew vertexBuilder = ItemWendewew.getGwintVewtexConsumew(vewtexConsumewPwovidew, modew.getWayew(getTextuwe()), false, false);
		modew.wendew(matwixStack, vertexBuilder, wight, OvewwayTextuwe.DEFAUWT_UV, 1, 1, 1, 1);
	}

	protected abstract BipedEntityModew<WivingEntity> getModew();

	protected abstract Identifiew getTextuwe();
}
