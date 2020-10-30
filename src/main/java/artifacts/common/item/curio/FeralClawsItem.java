package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ClawsModel;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableGloveCurio;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.UUID;

public class FeralClawsItem extends CurioArtifactItem {

	private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MODID, "textures/entity/curio/feral_claws_default.png");
	private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MODID, "textures/entity/curio/feral_claws_default.png");

	public static EntityAttributeModifier ATTACK_SPEED_MODIFIER = new EntityAttributeModifier(UUID.fromString("7a3367b2-0a38-491d-b5c7-338d5d0c1dd4"), Artifacts.MODID + ":feral_claws_attack_speed", 1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

	public FeralClawsItem() {
		super(new Item.Settings());
	}

	@Override
	public ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			@Override
			public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(String identifier) {
				Multimap<EntityAttribute, EntityAttributeModifier> result = super.getAttributeModifiers(identifier);
				result.put(EntityAttributes.GENERIC_ATTACK_SPEED, ATTACK_SPEED_MODIFIER);
				return result;
			}
		};
	}

	@Override
	public IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableGloveCurio() {
			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return TEXTURE_DEFAULT;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getSlimTexture() {
				return TEXTURE_SLIM;
			}

			@Environment(EnvType.CLIENT)
			protected ClawsModel getSlimModel() {
				if (modelSlim == null) {
					modelSlim = new ClawsModel(true);
				}
				return (ClawsModel) modelSlim;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected ClawsModel getModel() {
				if (modelDefault == null) {
					modelDefault = new ClawsModel(false);
				}
				return (ClawsModel) modelDefault;
			}
		};
	}
}
