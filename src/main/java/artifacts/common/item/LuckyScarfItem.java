package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.ScarfModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class LuckyScarfItem extends CurioArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/lucky_scarf.png");

    public LuckyScarfItem() {
        super(new Settings());
    }

    @Override
    ICurio attachCurio(ItemStack stack) {
        return new Curio(this);
    }

    @Override
    IRenderableCurio attachRenderableCurio(ItemStack stack) {
        return new RenderableCurio() {
            private Object model;

            @Override
            @Environment(EnvType.CLIENT)
            protected ScarfModel getModel() {
                if (model == null) {
                    model = new ScarfModel();
                }
                return (ScarfModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        };
    }

    // TODO: Mixin to add +1 fortune to loot table
}
