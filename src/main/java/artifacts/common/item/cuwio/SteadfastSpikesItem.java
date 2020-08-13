package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.cwient.wendew.modew.cuwio.SteadfastSpikesModel;
import artifacts.common.item.Cuwio;
import artifacts.common.item.WendewabweCuwio;
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

public class SteadfastSpikesItem extends CuwioAwtifactItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/steadfast_spikes.png");

	private static final EntityAttwibuteModifiew KNOCKBACK_RESISTANCE_MODIFIER = new EntityAttwibuteModifiew(UUID.fromString("2aa3958f-49f5-47ba-a707-a4679ad7ff17"), "artifacts:steadfast_spikes_knockback_resistance", 1, EntityAttwibuteModifiew.Opewation.ADDITION);

	public SteadfastSpikesItem() {
		super(new Item.Settings());
	}

	@Override
	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this) {
			@Override
			public Multimap<EntityAttwibute, EntityAttwibuteModifiew> getAttributeModifiers(String identifier) {
				Multimap<EntityAttwibute, EntityAttwibuteModifiew> result = super.getAttributeModifiers(identifier);
				result.put(EntityAttwibutes.GENEWIC_KNOCKBACK_WESISTANCE, KNOCKBACK_RESISTANCE_MODIFIER);
				return result;
			}
		};
	}

	@Override
	protected IRenderableCurio attachWendewabweCuwio(ItemStack stack) {
		return new WendewabweCuwio() {
			private Object modew;

			@Override
			@Environment(EnvType.CLIENT)
			protected SteadfastSpikesModel getModew() {
				if (modew == null) {
					modew = new SteadfastSpikesModel();
				}
				return (SteadfastSpikesModel) modew;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifiew getTextuwe() {
				return TEXTUWE;
			}
		};
	}
}
