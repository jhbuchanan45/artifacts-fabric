package artifacts.common.item;

import artifacts.Artifacts;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.UUID;

public class PowerGloveItem extends ArtifactItem {

    private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/power_glove_default.png");
    private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/power_glove_slim.png");

    private static final EntityAttributeModifier POWER_GLOVE_ATTACK_DAMAGE = new EntityAttributeModifier(UUID.fromString("15fab7b9-5916-460b-a8e8-8434849a0662"), "artifacts:power_glove_attack_damage", 4, EntityAttributeModifier.Operation.ADDITION);

    public PowerGloveItem() {
        super(new Settings(), "power_glove");
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new GloveCurio(this) {
            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(String identifier) {
                Multimap<EntityAttribute, EntityAttributeModifier> result = super.getAttributeModifiers(identifier);
                result.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, POWER_GLOVE_ATTACK_DAMAGE);
                return result;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getSlimTexture() {
                return TEXTURE_SLIM;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE_DEFAULT;
            }
        });
    }
}
