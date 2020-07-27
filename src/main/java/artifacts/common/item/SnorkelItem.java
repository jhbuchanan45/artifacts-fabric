package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.SnorkelModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class SnorkelItem extends ArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/snorkel.png");

    public SnorkelItem() {
        super(new Settings(), "snorkel");
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                if (!livingEntity.world.isClient && livingEntity.age % 15 == 0) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 39, 0, true, false));
                }
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected SnorkelModel getModel() {
                if (model == null) {
                    model = new SnorkelModel();
                }
                return (SnorkelModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        });
    }
}
