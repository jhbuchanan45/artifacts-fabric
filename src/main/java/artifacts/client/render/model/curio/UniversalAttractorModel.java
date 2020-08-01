package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class UniversalAttractorModel extends BipedEntityModel<LivingEntity> {

	public final ModelPart belt;
	public final ModelPart magnet;

	public UniversalAttractorModel() {
		super(0.5F, 0, 64, 32);

		setVisible(false);

		torso = new ModelPart(this, 0, 0);
		belt = new ModelPart(this, 0, 0);
		magnet = new ModelPart(this, 24, 6);

		ModelPart magnet1 = new ModelPart(this, 24, 0);
		ModelPart magnet2 = new ModelPart(this, 32, 0);

		belt.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);
		magnet.addCuboid(0, 0, 0, 6, 2, 2);
		magnet1.addCuboid(0, 2, 0, 2, 4, 2);
		magnet2.addCuboid(4, 2, 0, 2, 4, 2);

		magnet.setPivot(2, 18, -7);

		magnet.addChild(magnet1);
		magnet.addChild(magnet2);
		torso.addChild(belt);
		torso.addChild(magnet);
	}

	@Override
	public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		magnet.visible = false;
		torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		magnet.visible = true;
		matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		belt.visible = false;
		torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		belt.visible = true;
	}
}
