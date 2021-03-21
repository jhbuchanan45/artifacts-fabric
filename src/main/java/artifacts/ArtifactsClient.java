package artifacts;

import artifacts.client.render.MimicRenderer;
import artifacts.client.render.UmbrellaItemRenderer;
import artifacts.init.Entities;
import artifacts.init.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ArtifactsClient implements ClientModInitializer {

	public static final ModelIdentifier UMBRELLA_ICON_MODEL = new ModelIdentifier(
			new Identifier(Artifacts.MODID, "umbrella_icon"), "inventory");
	public static final ModelIdentifier UMBRELLA_HELD_MODEL = new ModelIdentifier(
			new Identifier(Artifacts.MODID, "umbrella_in_hand"), "inventory");

	@Override
	public void onInitializeClient() {
		// Mimic EntityRenderer
		EntityRendererRegistry.INSTANCE.register(Entities.MIMIC, (dispatcher, context) -> new MimicRenderer(dispatcher));

		// Register Umbrella dynamic item renderer
		UmbrellaItemRenderer umbrellaItemRenderer = new UmbrellaItemRenderer();
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(umbrellaItemRenderer);
		BuiltinItemRendererRegistry.INSTANCE.register(Items.UMBRELLA, umbrellaItemRenderer);

		// ModelPredicateProvider for rendering of Umbrella blocking
		FabricModelPredicateProviderRegistry.register(Items.UMBRELLA, new Identifier("blocking"), (stack, world, entity)
				-> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0);

		// Register Umbrella baked models
		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(UMBRELLA_ICON_MODEL));
		ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(UMBRELLA_HELD_MODEL));
	}
}
