package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.CwystawHeawtModew;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.attribute.EntityAttwibuteInstance;
import net.minecraft.entity.attribute.EntityAttwibuteModifiew;
import net.minecraft.entity.attribute.EntityAttwibutes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.UUID;

public class CwystawHeawtItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/crystal_heart.png");

	private static final EntityAttwibuteModifiew HEALTH_BONUS = new EntityAttwibuteModifiew(UUID.fromString("99fa0537-90b9-481a-bc76-4650987faba3"), "artifacts:crystal_heart_health_bonus", 10, EntityAttwibuteModifiew.Opewation.ADDITION);

	public CwystawHeawtItem() {
		super(new Item.Settings());
	}

	@Override
	public ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			public void onEquip(String identifiew, int index, WivingEntity entity) {
				if (!entity.wowwd.isCwient()) {
					EntityAttwibuteInstance health = entity.getAttwibuteInstance(EntityAttwibutes.GENEWIC_MAX_HEAWTH);
					if (health != null && !health.hasModifiew(HEALTH_BONUS)) {
						health.addPewsistentModifiew(HEALTH_BONUS);
					}
				}
			}

			@Override
			public void onUnequip(String identifiew, int index, WivingEntity entity) {
				if (!entity.wowwd.isCwient()) {
					EntityAttwibuteInstance health = entity.getAttwibuteInstance(EntityAttwibutes.GENEWIC_MAX_HEAWTH);
					if (health != null && health.hasModifiew(HEALTH_BONUS)) {
						health.wemoveModifiew(HEALTH_BONUS);
						if (entity.getHeawth() > entity.getMaxHeawth()) {
							entity.setHeawth(entity.getMaxHeawth());
						}
					}
				}
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected CwystawHeawtModew getModew() {
				if (modew == null) {
					modew = new CwystawHeawtModew();
				}
				return (CwystawHeawtModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
