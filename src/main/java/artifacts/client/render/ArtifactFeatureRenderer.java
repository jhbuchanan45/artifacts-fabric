package artifacts.client.render;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Custom trinkets feature renderer, {@link dev.emi.trinkets.TrinketFeatureRenderer} is missing parameters
 */
public class ArtifactFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
	private final FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context;

	public ArtifactFeatureRenderer(
			FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
		super(context);
		this.context = context;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		TrinketsApi.getTrinketComponent(player).ifPresent(component ->
		component.forEach((slotReference, stack) ->
				TrinketRendererRegistry.getRenderer(stack.getItem()).ifPresent(renderer -> {
					matrices.push();
					renderer.render(stack, slotReference, this.getContextModel(), matrices, vertexConsumers,
							light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
					matrices.pop();
				})
		)
);
	}
}
