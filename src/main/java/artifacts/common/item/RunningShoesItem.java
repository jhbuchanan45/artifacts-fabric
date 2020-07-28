package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.RunningShoesModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.UUID;

public class RunningShoesItem extends CurioArtifactItem {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/curio/running_shoes.png");

    private static final EntityAttributeModifier RUNNING_SHOES_SPEED_BOOST = new EntityAttributeModifier(UUID.fromString("ac7ab816-2b08-46b6-879d-e5dea34ff305"), "artifacts:running_shoes_movement_speed", 0.4, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    public RunningShoesItem() {
        super(new Settings());
    }

    @Override
    ICurio attachCurio(ItemStack stack) {
        return new Curio(this) {
            @Override
            @SuppressWarnings("ConstantConditions")
            public void curioTick(String identifier, int index, LivingEntity livingEntity) {
                EntityAttributeInstance movementSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                if (livingEntity.isSprinting()) {
                    if (!movementSpeed.hasModifier(RUNNING_SHOES_SPEED_BOOST)) {
                        movementSpeed.addTemporaryModifier(RUNNING_SHOES_SPEED_BOOST);
                    }
                    if (livingEntity instanceof PlayerEntity) {
                        livingEntity.stepHeight = Math.max(livingEntity.stepHeight, 1.1F);
                    }
                } else if (movementSpeed.hasModifier(RUNNING_SHOES_SPEED_BOOST)) {
                    movementSpeed.removeModifier(RUNNING_SHOES_SPEED_BOOST);
                    livingEntity.stepHeight = 0.6F;
                }
            }

            @Override
            @SuppressWarnings("ConstantConditions")
            public void onUnequip(String identifier, int index, LivingEntity livingEntity) {
                EntityAttributeInstance movementSpeed = livingEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                if (movementSpeed.hasModifier(RUNNING_SHOES_SPEED_BOOST)) {
                    movementSpeed.removeModifier(RUNNING_SHOES_SPEED_BOOST);
                    livingEntity.stepHeight = 0.6F;
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
            protected RunningShoesModel getModel() {
                if (model == null) {
                    model = new RunningShoesModel();
                }
                return (RunningShoesModel) model;
            }

            @Override
            @Environment(EnvType.CLIENT)
            protected Identifier getTexture() {
                return TEXTURE;
            }
        };
    }
}
