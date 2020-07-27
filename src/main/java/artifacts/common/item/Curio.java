package artifacts.common.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

abstract class Curio implements ICurio {

    private final Item curioItem;
    private final SoundEvent equipSound = getEquipSound();

    public Curio(Item item) {
        curioItem = item;
    }

    public static ICapabilityProvider createProvider(ICurio curio) {
        return new Curio.Provider(curio);
    }

    protected SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public void playRightClickEquipSound(LivingEntity entity) {
        entity.world.playSound(null, new BlockPos(entity.getPos()), equipSound, SoundCategory.NEUTRAL, 1, 1);
    }

    public boolean canRightClickEquip() {
        return true;
    }

    @Override
    public boolean canEquip(String identifier, LivingEntity entity) {
        return !CuriosApi.getCuriosHelper().findEquippedCurio(curioItem, entity).isPresent();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
        return true;
    }


    @Override
    @Environment(EnvType.CLIENT)
    public void render(String identifier, int index, MatrixStack matrixStack, VertexConsumerProvider renderTypeBuffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        BipedEntityModel<LivingEntity> model = getModel();
        model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.animateModel(entity, limbSwing, limbSwingAmount, partialTicks);
        ICurio.RenderHelper.followBodyRotations(entity, model);
        VertexConsumer vertexBuilder = ItemRenderer.getArmorVertexConsumer(renderTypeBuffer, model.getLayer(getTexture()), false, false);
        model.render(matrixStack, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }

    @Environment(EnvType.CLIENT)
    protected abstract BipedEntityModel<LivingEntity> getModel();

    @Environment(EnvType.CLIENT)
    protected abstract Identifier getTexture();

    protected static class Provider implements ICapabilityProvider {

        private final LazyOptional<ICurio> capability;

        Provider(ICurio curio) {
            capability = LazyOptional.of(() -> curio);
        }

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return CuriosCapability.ITEM.orEmpty(cap, capability);
        }
    }
}
