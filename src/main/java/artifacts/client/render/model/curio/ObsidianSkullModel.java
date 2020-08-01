package artifacts.client.render.model.curio;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class ObsidianSkullModel extends BipedEntityModel<LivingEntity> {

	public final ModelPart belt;
	public final ModelPart skull;

	public ObsidianSkullModel() {
		super(0.5F, 0, 32, 32);

		setVisible(false);

		torso = new ModelPart(this, 0, 0);
		belt = new ModelPart(this, 0, 0);
		skull = new ModelPart(this, 0, 16);

		ModelPart skull2 = new ModelPart(this, 0, 28);
		ModelPart skull3 = new ModelPart(this, 24, 0);
		ModelPart skull4 = new ModelPart(this, 24, 3);
		ModelPart skull5 = new ModelPart(this, 24, 6);
		ModelPart skull6 = new ModelPart(this, 16, 28);
		ModelPart skull7 = new ModelPart(this, 24, 9);
		ModelPart skull8 = new ModelPart(this, 24, 13);
		ModelPart skull9 = new ModelPart(this, 24, 17);

		belt.addCuboid(-4, 0, -2, 8, 12, 4, 0.5F);

		skull.addCuboid(0, 0, 1, 7, 6, 6);
		skull2.addCuboid(0, 0, 0, 7, 2, 1);
		skull3.addCuboid(0, 2, 0, 1, 2, 1);
		skull4.addCuboid(3, 2, 0, 1, 2, 1);
		skull5.addCuboid(6, 2, 0, 1, 2, 1);
		skull6.addCuboid(0, 4, 0, 7, 2, 1);
		skull7.addCuboid(1, 6, 0, 1, 1, 3);
		skull8.addCuboid(3, 6, 0, 1, 1, 3);
		skull9.addCuboid(5, 6, 0, 1, 1, 3);

		skull.setPivot(6, 18, -9);
		skull.yaw = -0.5F;

		skull.addChild(skull2);
		skull.addChild(skull3);
		skull.addChild(skull4);
		skull.addChild(skull5);
		skull.addChild(skull6);
		skull.addChild(skull7);
		skull.addChild(skull8);
		skull.addChild(skull9);

		torso.addChild(belt);
		torso.addChild(skull);
	}

	@Override
	public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		skull.visible = false;
		torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		skull.visible = true;
		matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		belt.visible = false;
		torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		belt.visible = true;
	}
}
