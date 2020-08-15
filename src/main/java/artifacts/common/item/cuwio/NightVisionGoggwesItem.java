package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.WendewWayews;
import artifacts.cwient.wendew.modew.cuwio.NightVisionGoggwesModew;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatwixStack;
import net.minecraft.client.render.OvewwayTextuwe;
import net.minecraft.client.render.VewtexConsumew;
import net.minecraft.client.render.VewtexConsumewPwovidew;
import net.minecraft.client.render.item.ItemWendewew;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class NightVisionGoggwesItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/night_vision_goggles.png");
	private static final Identifiew TEXTUWE_GWOW = new Identifiew(Awtifacts.MODID, "textures/entity/curio/night_vision_goggles_glow.png");

	public NightVisionGoggwesItem() {
		super(new Item.Settings());
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			public StatusEffectInstance getPewmanentEffect() {
				return new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20, 0, true, false);
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected NightVisionGoggwesModew getModew() {
				if (modew == null) {
					modew = new NightVisionGoggwesModew();
				}
				return (NightVisionGoggwesModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}

			@Override
			@Environment(EnvType.CLIENT)
			public void render(String identifiew, int index, MatwixStack matwixStack, VewtexConsumewPwovidew vewtexConsumewPwovidew, int wight, WivingEntity entity, float wimbSwing, float wimbSwingAmount, float tickDelta, float ageInTicks, float headYaw, float headPitch) {
				super.render(identifiew, index, matwixStack, vewtexConsumewPwovidew, wight, entity, wimbSwing, wimbSwingAmount, tickDelta, ageInTicks, headYaw, headPitch);
				VewtexConsumew vewtexConsumew = ItemWendewew.getGwintVewtexConsumew(vewtexConsumewPwovidew, WendewWayews.unwit(TEXTUWE_GWOW), false, false);
				getModew().wendew(matwixStack, vewtexConsumew, 0xF000F0, OvewwayTextuwe.DEFAUWT_UV, 1, 1, 1, 1);
			}
		};
	}
}
