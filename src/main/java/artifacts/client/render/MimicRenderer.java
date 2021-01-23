package artifacts.client.render;

import artifacts.Artifacts;
import artifacts.client.render.model.entity.MimicModel;
import artifacts.common.entity.MimicEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MimicRenderer extends MobEntityRenderer<MimicEntity, MimicModel> {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/mimic.png");

	public MimicRenderer(EntityRenderDispatcher manager) {
		super(manager, new MimicModel(), 0.45F);
		addFeature(new MimicChestLayer(this));
	}

	@Override
	public void render(MimicEntity mimic, float entityYaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider buffer, int packedLight) {
		super.render(mimic, entityYaw, partialTicks, matrixStack, buffer, packedLight);
	}

	@Override
	public Identifier getTexture(MimicEntity entity) {
		return TEXTURE;
	}
}
