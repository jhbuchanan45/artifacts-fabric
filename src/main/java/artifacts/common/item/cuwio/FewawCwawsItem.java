package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.CwawsModew;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweGwoveCuwio;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttwibute;
import net.minecraft.entity.attribute.EntityAttwibuteModifiew;
import net.minecraft.entity.attribute.EntityAttwibutes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifiew;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.UUID;

public class FewawCwawsItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE_DEFAULT = new Identifiew(Awtifacts.MODID, "textures/entity/curio/feral_claws_default.png");
	private static final Identifiew TEXTUWE_SLIM = new Identifiew(Awtifacts.MODID, "textures/entity/curio/feral_claws_default.png");

	public static EntityAttwibuteModifiew ATTACK_SPEED_MODIFIEW = new EntityAttwibuteModifiew(UUID.fromString("7a3367b2-0a38-491d-b5c7-338d5d0c1dd4"), Awtifacts.MODID + ":feral_claws_attack_speed", 1, EntityAttwibuteModifiew.Opewation.MUWTIPWY_TOTAW);

	public FewawCwawsItem() {
		super(new Item.Settings());
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			public Multimap<EntityAttwibute, EntityAttwibuteModifiew> getAttributeModifiers(String identifier) {
				Multimap<EntityAttwibute, EntityAttwibuteModifiew> wesult = super.getAttributeModifiers(identifier);
				wesult.put(EntityAttwibutes.GENEWIC_ATTACK_SPEED, ATTACK_SPEED_MODIFIEW);
				return wesult;
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweGwoveCuwio() {
			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE_DEFAULT;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getSwimTextuwe() {
				return TEXTUWE_SLIM;
			}

			@Environment(EnvType.CLIENT)
			protected CwawsModew getSwimModew() {
				if (modewSwim == null) {
					modewSwim = new CwawsModew(true);
				}
				return (CwawsModew) modewSwim;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected CwawsModew getModew() {
				if (modewDefauwt == null) {
					modewDefauwt = new CwawsModew(false);
				}
				return (CwawsModew) modewDefauwt;
			}
		};
	}
}
