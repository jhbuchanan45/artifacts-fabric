package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ClawsModel;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class FeralClawsItem extends ArtifactItem {

    private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/feral_claws_default.png");
    private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/feral_claws_default.png");

    public static EntityAttributeModifier FERAL_CLAWS_ATTACK_SPEED = new EntityAttributeModifier(UUID.fromString("7a3367b2-0a38-491d-b5c7-338d5d0c1dd4"), "artifacts:feral_claws_attack_speed", 1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public FeralClawsItem() {
        super(new Settings());
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new GloveCurio(this) {

            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(String identifier) {
                Multimap<EntityAttribute, EntityAttributeModifier> result = super.getAttributeModifiers(identifier);
                result.put(EntityAttributes.GENERIC_ATTACK_SPEED, FERAL_CLAWS_ATTACK_SPEED);
                return result;
            }

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
                if (model_slim == null) {
                    model_slim = new ClawsModel(true);
                }
                return (ClawsModel) model_slim;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected ClawsModel getModel() {
                if (model_default == null) {
                    model_default = new ClawsModel(false);
                }
                return (ClawsModel) model_default;
            }
        });
    }
}
