package artifacts.client.render.model.trinket;

import net.minecraft.client.model.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

public class ScarfModel extends BipedEntityModel<LivingEntity> {

	private final ModelPart bipedCape;

	public ScarfModel(ModelPart root) {
		this(root, RenderLayer::getEntityCutoutNoCull);
	}

	public ScarfModel(ModelPart root, Function<Identifier, RenderLayer> renderType) {
		super(root, renderType);
		bipedCape = root.getChild("biped_cape");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(new Dilation(0.5F), 0);
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder body = ModelPartBuilder.create().uv(0, 16).cuboid(-6.01F, -2, -4, 12, 6, 8);
		ModelPartBuilder bipedCape = ModelPartBuilder.create().uv(32, 0).cuboid(-5, 0, 0, 5, 12, 2);

		root.addChild("body", body, ModelTransform.NONE);
		root.addChild("biped_cape", bipedCape, ModelTransform.NONE);

		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void setAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		bipedCape.copyTransform(body);
		bipedCape.pivotZ += 1.99F;
	}

	@Override
	public void animateModel(LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		if (entity instanceof AbstractClientPlayerEntity) {
			AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) entity;
			double x = MathHelper.lerp(partialTicks, player.prevCapeX, player.capeX) - MathHelper.lerp(partialTicks, player.prevX, player.getX());
			double y = MathHelper.lerp(partialTicks, player.prevCapeY, player.capeY) - MathHelper.lerp(partialTicks, player.prevY, player.getY());
			double z = MathHelper.lerp(partialTicks, player.prevCapeZ, player.capeZ) - MathHelper.lerp(partialTicks, player.prevZ, player.getZ());
			float f = player.prevBodyYaw + (player.bodyYaw - player.prevBodyYaw);
			double d3 = MathHelper.sin(f * ((float) Math.PI / 180));
			double d4 = -MathHelper.cos(f * ((float) Math.PI / 180));
			float f1 = (float) y * 10;
			f1 = MathHelper.clamp(f1, -6, 32);
			float f2 = (float) (x * d3 + z * d4) * 100;
			f2 = MathHelper.clamp(f2, 0, 150);
			if (f2 < 0) {
				f2 = 0;
			}

			float f4 = MathHelper.lerp(partialTicks, player.prevStrideDistance, player.strideDistance);
			f1 = f1 + MathHelper.sin(MathHelper.lerp(partialTicks, player.prevHorizontalSpeed, player.horizontalSpeed) * 6) * 32 * f4;

			bipedCape.pitch += (6 + f2 / 2 + f1) / 180 * (float) Math.PI;
		}
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bipedCape.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
