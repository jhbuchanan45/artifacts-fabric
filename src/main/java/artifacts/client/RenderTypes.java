package artifacts.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

public abstract class RenderTypes extends RenderLayer {

    private RenderTypes(String name, VertexFormat fmt, int glMode, int size, boolean doCrumbling, boolean depthSorting, Runnable onEnable, Runnable onDisable) {
        super(name, fmt, glMode, size, doCrumbling, depthSorting, onEnable, onDisable);
        throw new IllegalStateException();
    }

    public static RenderLayer unlit(Identifier textureLocation) {
        MultiPhaseParameters renderState = MultiPhaseParameters.builder()
                .texture(new Texture(textureLocation, false, false))
                .transparency(NO_TRANSPARENCY)
                .alpha(ONE_TENTH_ALPHA)
                .cull(DISABLE_CULLING)
                .lightmap(ENABLE_LIGHTMAP)
                .overlay(ENABLE_OVERLAY_COLOR)
                .build(true);
        return of("artifacts_entity_unlit", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, GL11.GL_QUADS, 256, true, false, renderState);
    }
}
