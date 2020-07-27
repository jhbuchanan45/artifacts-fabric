package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.CrossNecklaceModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CrossNecklaceItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/cross_necklace.png");

    public CrossNecklaceItem() {
        super(new Settings(), "cross_necklace");
    }

    private static boolean canApplyBonus(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("CanApplyBonus");
    }

    private static void setCanApplyBonus(ItemStack stack, boolean canApplyBonus) {
        stack.getOrCreateTag().putBoolean("CanApplyBonus", canApplyBonus);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

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
        });
    }
}
