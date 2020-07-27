package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.SteadfastSpikesModel;
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

public class SteadfastSpikesItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/steadfast_spikes.png");

    private static final EntityAttributeModifier STEADFAST_SPIKES_KNOCKBACK_RESISTANCE = new EntityAttributeModifier(UUID.fromString("2aa3958f-49f5-47ba-a707-a4679ad7ff17"), "artifacts:steadfast_spikes_knockback_resistance", 1, EntityAttributeModifier.Operation.ADDITION);

    public SteadfastSpikesItem() {
        super(new Settings(), "steadfast_spikes");
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(String identifier) {
                Multimap<EntityAttribute, EntityAttributeModifier> result = super.getAttributeModifiers(identifier);
                result.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, STEADFAST_SPIKES_KNOCKBACK_RESISTANCE);
                return result;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected SteadfastSpikesModel getModel() {
                if (model == null) {
                    model = new SteadfastSpikesModel();
                }
                return (SteadfastSpikesModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        });
    }
}
