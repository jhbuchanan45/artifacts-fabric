package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
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

public class PowewGwoveItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE_DEFAULT = new Identifiew(Awtifacts.MODID, "textures/entity/curio/power_glove_default.png");
	private static final Identifiew TEXTUWE_SLIM = new Identifiew(Awtifacts.MODID, "textures/entity/curio/power_glove_slim.png");

	private static final EntityAttwibuteModifiew ATTACK_DAMAGE_MODIFIER = new EntityAttwibuteModifiew(UUID.fromString("15fab7b9-5916-460b-a8e8-8434849a0662"), "artifacts:power_glove_attack_damage", 4, EntityAttwibuteModifiew.Opewation.ADDITION);

	public PowewGwoveItem() {
		super(new Item.Settings());
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			public Multimap<EntityAttwibute, EntityAttwibuteModifiew> getAttributeModifiers(String identifier) {
				Multimap<EntityAttwibute, EntityAttwibuteModifiew> result = super.getAttributeModifiers(identifier);
				result.put(EntityAttwibutes.GENEWIC_ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER);
				return result;
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweGwoveCuwio() {
			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getSwimTextuwe() {
				return TEXTUWE_SLIM;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE_DEFAULT;
			}
		};
	}
}
