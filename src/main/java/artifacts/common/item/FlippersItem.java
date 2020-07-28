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
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.UUID;

public class FlippersItem extends CurioArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/flippers.png");

    private static final EntityAttributeModifier FLIPPER_SWIM_SPEED = new EntityAttributeModifier(UUID.fromString("63f1bb32-d301-419b-ab52-5d1af94eed1d"), "artifacts:flipper_swim_speed", 1, EntityAttributeModifier.Operation.ADDITION);


    public FlippersItem() {
        super(new Item.Settings());
    }

    @Override
    ICurio attachCurio(ItemStack stack) {
        return new Curio(this) {
            @Override
            public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(String identifier) {
                Multimap<EntityAttribute, EntityAttributeModifier> result = super.getAttributeModifiers(identifier);
                // TODO: wots dis
                //result.put(ForgeMod.SWIM_SPEED.get(), FLIPPER_SWIM_SPEED);
                return result;
            }
        };
    }

    @Override
    IRenderableCurio attachRenderableCurio(ItemStack stack) {
        return new RenderableCurio() {
            private Object model;

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
        };
    }
}
