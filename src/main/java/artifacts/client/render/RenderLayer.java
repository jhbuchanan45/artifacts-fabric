package artifacts.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class RenderLayer extends net.minecraft.client.render.RenderLayer {

	private RenderLayer(String name, VertexFormat fmt, VertexFormat.DrawMode drawMode, int size, boolean doCrumbling, boolean depthSorting, Runnable onEnable, Runnable onDisable) {
		super(name, fmt, drawMode, size, doCrumbling, depthSorting, onEnable, onDisable);
		throw new IllegalStateException();
	}

	public static net.minecraft.client.render.RenderLayer unlit(Identifier textureLocation) {
		MultiPhaseParameters renderState = MultiPhaseParameters.builder()
				.shader(Shader.ENTITY_ALPHA_SHADER)
				.texture(new Texture(textureLocation, false, false))
				.transparency(NO_TRANSPARENCY)
				.cull(DISABLE_CULLING)
				.lightmap(ENABLE_LIGHTMAP)
				.overlay(ENABLE_OVERLAY_COLOR)
				.build(true);
		// TODO - Find cleaner solution that doesn't use mixins
		return RenderLayer.of("artifacts_entity_unlit", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, false, renderState);
	}
}
