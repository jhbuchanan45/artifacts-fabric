package artifacts.client.render.model.trinket;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;

public class GloveModel extends PlayerEntityModel<LivingEntity> {

	public GloveModel(boolean smallArms) {
		super(0.5F, smallArms);

		setVisible(false);
	}

	public void renderHand(boolean mainHand, MatrixStack matrices, VertexConsumer vertexConsumers, int light, int overlay, float red, float green, float blue, float alpha) {
		boolean isRightArm = (MinecraftClient.getInstance().options.mainArm == Arm.LEFT) != mainHand;
		rightSleeve.copyTransform(rightArm);
		leftSleeve.copyTransform(leftArm);
		leftArm.visible = !isRightArm;
		leftSleeve.visible = !isRightArm;
		rightArm.visible = isRightArm;
		rightSleeve.visible = isRightArm;
		render(matrices, vertexConsumers, light, overlay, red, green, blue, alpha);
	}
}
