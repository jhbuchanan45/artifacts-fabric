package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class AntidoteVesselModel extends BipedEntityModel<LivingEntity> {

	public final ModelPart belt;
	public final ModelPart jar;

	public AntidoteVesselModel() {
		super(0.5F, 0, 64, 32);

		setVisible(false);

		torso = new ModelPart(this, 0, 0);
		belt = new ModelPart(this, 0, 0);
		jar = new ModelPart(this, 0, 16);

		ModelPart lid = new ModelPart(this, 24, 0);

		belt.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		jar.addCuboid(0, 0, 0, 6, 8, 6);
		lid.addCuboid(1, -1, 1, 4, 1, 4);

		jar.setPivot(6, 18, -10);
		jar.yaw = -0.5F;

		jar.addChild(lid);
		torso.addChild(belt);
		torso.addChild(jar);
	}

	@Override
	public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		jar.visible = false;
		torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		jar.visible = true;
		matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		belt.visible = false;
		torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		belt.visible = true;
	}
}
