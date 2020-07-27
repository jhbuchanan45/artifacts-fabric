package artifacts.client.render;

import artifacts.Artifacts;
import artifacts.client.render.model.entity.MimicModel;
import artifacts.common.entity.MimicEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MimicRenderer extends MobEntityRenderer<MimicEntity, MimicModel> {

    private static final Identifier TEXTURE = new Identifier(Artifacts.MOD_ID, "textures/entity/mimic.png");

    public MimicRenderer(EntityRenderDispatcher manager) {
        super(manager, new MimicModel(), 0.45F);
    }

    @Override
    public Identifier getTexture(MimicEntity entity) {
        return TEXTURE;
    }
}
