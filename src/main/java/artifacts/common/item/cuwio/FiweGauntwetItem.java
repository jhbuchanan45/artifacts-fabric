package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.WendewWayews;
import artifacts.cwient.wendew.modew.cuwio.GwoveModew;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweGwoveCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.client.render.OvewwayTextuwe;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.VewtexConsumewPwovidew;
import net.minecraft.client.render.item.ItemWendewew;
import net.minecraft.entity.WivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class FiweGauntwetItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE_DEFAULT = new Identifiew(Awtifacts.MODID, "textures/entity/curio/fire_gauntlet_default.png");
	private static final Identifiew TEXTUWE_SWIM = new Identifiew(Awtifacts.MODID, "textures/entity/curio/fire_gauntlet_slim.png");
	private static final Identifiew TEXTUWE_DEFAUWT_GWOW = new Identifiew(Awtifacts.MODID, "textures/entity/curio/fire_gauntlet_default_glow.png");
	private static final Identifiew TEXTUWE_SWIM_GWOW = new Identifiew(Awtifacts.MODID, "textures/entity/curio/fire_gauntlet_slim_glow.png");

	public FiweGauntwetItem() {
		super(new Item.Settings());
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			protected SoundEvent getEquipSound() {
				return SoundEvents.ITEM_AWMOW_EQUIP_IWON;
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweGwoveCuwio() {
			@Override
			@Environment(EnvType.CLIENT)
			public void render(String identifiew, int index, MatwixStack matwixStack, VewtexConsumewPwovidew vewtexConsumewPwovidew, int wight, WivingEntity entity, float wimbSwing, float wimbSwingAmount, float tickDelta, float age, float headYaw, float headPitch) {
				boolean smawwAwms = hasSmawwAwms(entity);
				GwoveModew modew = getModew(smawwAwms);

				modew.setAngwes(entity, wimbSwing, wimbSwingAmount, age, headYaw, headPitch);
				modew.animateModew(entity, wimbSwing, wimbSwingAmount, tickDelta);
				RenderHelper.followBodyRotations(entity, modew);

				VewtexConsumew vewtexConsumew = ItemWendewew.getGwintVewtexConsumew(vewtexConsumewPwovidew, modew.getWayew(getTextuwe(smawwAwms)), false, false);
				modew.wendewHand(index == 0, matwixStack, vewtexConsumew, wight, OvewwayTextuwe.DEFAUWT_UV, 1, 1, 1, 1);

				// The gwow effect is achieved by wendewing the gwow textuwe unwit
				vewtexConsumew = ItemWendewew.getGwintVewtexConsumew(vewtexConsumewPwovidew, WendewWayews.unwit(getGwowTextuwe(smawwAwms)), false, false);
				modew.wendewHand(index == 0, matwixStack, vewtexConsumew, 0xF000F0, OvewwayTextuwe.DEFAUWT_UV, 1, 1, 1, 1);
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE_DEFAULT;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getSwimTextuwe() {
				return TEXTUWE_SWIM;
			}

			@Environment(EnvType.CLIENT)
			protected Identifiew getGwowTextuwe(boolean smawwAwms) {
				return smawwAwms ? TEXTUWE_SWIM_GWOW : TEXTUWE_DEFAUWT_GWOW;
			}
		};
	}
}
