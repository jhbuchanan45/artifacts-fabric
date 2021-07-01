package artifacts.client.render.model.entity;

import artifacts.entity.MimicEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class MimicChestLayerModel extends EntityModel<MimicEntity> {

	protected final ModelPart bottom;
	protected final ModelPart lid;
	protected final ModelPart latch;

	public MimicChestLayerModel(ModelPart root) {
		bottom = root.getChild("bottom");
		lid = root.getChild("lid");
		latch = root.getChild("latch");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();

		ModelPartBuilder bottom = ModelPartBuilder.create().uv(0, 19).cuboid(1, -9, 0, 14, 10, 14);
		ModelPartBuilder lid = ModelPartBuilder.create().uv(0, 0).cuboid(1, 0, 0, 14, 5, 14);
		ModelPartBuilder latch = ModelPartBuilder.create().uv(0, 0).cuboid(7, -1, 15, 2, 4, 1);

		root.addChild("bottom", bottom, ModelTransform.pivot(0, 9, 1));
		root.addChild("lid", lid, ModelTransform.pivot(0, 9, 1));
		root.addChild("latch", latch, ModelTransform.pivot(0, 8, 0));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(MimicEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public void animateModel(MimicEntity mimic, float limbAngle, float limbDistance, float tickDelta) {
		if (mimic.ticksInAir > 0) {
			lid.pitch = latch.pitch = Math.max(-60, (mimic.ticksInAir - 1 + tickDelta) * -6) * 0.0174533F;
			bottom.pitch = Math.min(30, (mimic.ticksInAir - 1 + tickDelta) * 3) * 0.0174533F;
		} else {
			lid.pitch = latch.pitch = 0;
			bottom.pitch = 0;
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		bottom.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		lid.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		latch.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}
