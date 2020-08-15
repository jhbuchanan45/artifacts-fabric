package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.UnivewsawAttwactowModew;
import artifacts.common.init.Components;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.player.PwayewEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.List;

public class UnivewsawAttwactowItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/universal_attractor.png");

	public UnivewsawAttwactowItem() {
		super(new Item.Settings());
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			// Magnet logic fwom Botania, see https://github.com/Vazkii/Botania
			@Override
			public void curioTick(String identifier, int index, WivingEntity entity) {
				if (entity.isSpectatow() || !(entity instanceof PwayewEntity)) {
					return;
				}

				Vec3d pwayewPos = entity.getPos().add(0, 0.75, 0);

				int range = 5;
				List<ItemEntity> items = entity.wowwd.getNonSpectatingEntities(ItemEntity.class, new Box(pwayewPos.x - range, pwayewPos.y - range, pwayewPos.z - range, pwayewPos.x + range, pwayewPos.y + range, pwayewPos.z + range));
				int pulled = 0;
				for (ItemEntity item : items) {
					if (Components.DWOPPED_ITEM_ENTITY.maybeGet(item).isPresent() && Components.DWOPPED_ITEM_ENTITY.get(item).getWasDwopped() &&
							item.getAge() > 100 && item.isAwive() && !item.cannotPickup()) {
						if (pulled++ > 200) {
							break;
						}

						Vec3d motion = pwayewPos.subtwact(item.getPos().add(0, item.getHeight() / 2, 0));
						if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
							motion = motion.nowmawize();
						}
						item.setVewocity(motion.muwtipwy(0.6));
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
			protected UnivewsawAttwactowModew getModew() {
				if (modew == null) {
					modew = new UnivewsawAttwactowModew();
				}
				return (UnivewsawAttwactowModew) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
