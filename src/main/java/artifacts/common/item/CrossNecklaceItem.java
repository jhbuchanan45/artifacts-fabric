package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.CrossNecklaceModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class CrossNecklaceItem extends CurioArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/cross_necklace.png");

    public CrossNecklaceItem() {
        super(new Settings());
    }

    private static boolean canApplyBonus(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("CanApplyBonus");
    }

    private static void setCanApplyBonus(ItemStack stack, boolean canApplyBonus) {
        stack.getOrCreateTag().putBoolean("CanApplyBonus", canApplyBonus);
    }

    @Override
    ICurio attachCurio(ItemStack stack) {
        return new Curio(this) {
            @Override
            public void curioTick(String identifier, int index, LivingEntity entity) {
                if (entity.timeUntilRegen <= 10) {
                    setCanApplyBonus(stack, true);
                } else {
                    if (canApplyBonus(stack)) {
                        entity.timeUntilRegen += 20;
                        setCanApplyBonus(stack, false);
                    }
                }
            }
        };
    }

    @Override
    IRenderableCurio attachRenderableCurio(ItemStack stack) {
        return new RenderableCurio() {
            private Object model;

            @Override
            @Environment(EnvType.CLIENT)
            protected CrossNecklaceModel getModel() {
                if (model == null) {
                    model = new CrossNecklaceModel();
                }
                return (CrossNecklaceModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        };
    }
}
