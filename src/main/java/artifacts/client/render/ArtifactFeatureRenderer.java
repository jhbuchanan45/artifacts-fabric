package artifacts.client.render;

import artifacts.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketSlots;
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
		TrinketComponent comp = TrinketsApi.getTrinketComponent(player);
		List<String> names = TrinketSlots.getAllSlotNames();

		for (int i = 0; i < comp.getInventory().size(); i++) {
			matrices.push();
			ItemStack stack = comp.getInventory().getStack(i);
			if (stack.getItem() instanceof TrinketArtifactItem) {
				((TrinketArtifactItem) stack.getItem()).render(names.get(i), matrices, vertexConsumers, light, context.getModel(), player, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
			}
			matrices.pop();
		}
	}
}
