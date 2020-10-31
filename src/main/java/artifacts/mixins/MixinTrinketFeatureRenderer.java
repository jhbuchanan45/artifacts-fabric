package artifacts.mixins;

import artifacts.common.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.TrinketFeatureRenderer;
import dev.emi.trinkets.api.TrinketComponent;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

/**
 * Custom trinkets feature renderer: the one in trinkets api is missing parameters
 */
@Mixin(value = TrinketFeatureRenderer.class, remap = false)
public abstract class MixinTrinketFeatureRenderer {

	@Shadow private FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context;

	@Inject(method = "render", locals = LocalCapture.CAPTURE_FAILSOFT, at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;popMatrix()V"))
	private void extendedRender(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player,
	                            float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw,
	                            float headPitch, CallbackInfo info, TrinketComponent comp, List<String> names, int i, ItemStack stack) {
		if (stack.getItem() instanceof TrinketArtifactItem) {
			((TrinketArtifactItem) stack.getItem()).render(names.get(i), matrices, vertexConsumers, light, context.getModel(), player, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
		}
	}
}
