package artifacts.client.render;

import artifacts.Artifacts;
import artifacts.ArtifactsClient;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.Collections;

public class UmbrellaItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, SimpleSynchronousResourceReloadListener {
	private ItemRenderer itemRenderer;
	private BakedModel umbrellaIconModel;
	private BakedModel umbrellaHeldModel;

	@Override
	public Identifier getFabricId() {
		return new Identifier(Artifacts.MODID, "umbrella_renderer");
	}

	@Override
	public Collection<Identifier> getFabricDependencies() {
		return Collections.singletonList(ResourceReloadListenerKeys.MODELS);
	}

	@Override
	public void apply(ResourceManager manager) {
		MinecraftClient mc = MinecraftClient.getInstance();
		this.itemRenderer = mc.getItemRenderer();
		this.umbrellaIconModel = mc.getBakedModelManager().getModel(ArtifactsClient.UMBRELLA_ICON_MODEL);
		this.umbrellaHeldModel = mc.getBakedModelManager().getModel(ArtifactsClient.UMBRELLA_HELD_MODEL);
	}

	@Override
	public void render(ItemStack stack, ModelTransformation.Mode renderMode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		boolean iconModel = renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND
				|| renderMode == ModelTransformation.Mode.FIXED;
		BakedModel umbrellaModel = iconModel ? this.umbrellaIconModel : this.umbrellaHeldModel;

		matrices.pop(); // Cancel the previous transformation and pray that we are not breaking the state
		matrices.push();
		itemRenderer.renderItem(stack, renderMode, false, matrices, vertexConsumers, light, overlay, umbrellaModel);
	}
}
