package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.FlippersModel;
import com.google.common.collect.Multimap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.UUID;

public class FlippersItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/flippers.png");

    private static final EntityAttributeModifier FLIPPER_SWIM_SPEED = new EntityAttributeModifier(UUID.fromString("63f1bb32-d301-419b-ab52-5d1af94eed1d"), "artifacts:flipper_swim_speed", 1, EntityAttributeModifier.Operation.ADDITION);


    public FlippersItem() {
        super(new Item.Settings(), "flippers");
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(String identifier) {
                Multimap<EntityAttribute, EntityAttributeModifier> result = super.getAttributeModifiers(identifier);
                result.put(ForgeMod.SWIM_SPEED.get(), FLIPPER_SWIM_SPEED);
                return result;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected FlippersModel getModel() {
                if (model == null) {
                    model = new FlippersModel();
                }
                return (FlippersModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        });
    }
}
