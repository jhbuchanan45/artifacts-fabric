package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
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

	public ScarfModel() {
		this(RenderLayer::getEntityCutoutNoCull);
	}

	public ScarfModel(Function<Identifier, RenderLayer> renderType) {
		super(renderType, 0.5F, 0, 64, 32);
		bipedCape = new ModelPart(this, 32, 0);
		bipedCape.addCuboid(-5, 0, 0, 5, 12, 2);
		torso = new ModelPart(this, 0, 16);
		torso.addCuboid(-6.01F, -2, -4, 12, 6, 8);
	}

	@Override
	public void setAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		bipedCape.copyPositionAndRotation(torso);
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
		torso.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
