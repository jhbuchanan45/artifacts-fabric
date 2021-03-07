package artifacts.mixin.mixins.item.umbrella.client;

import artifacts.init.Items;
import artifacts.item.UmbrellaItem;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

	@Shadow @Final private ItemModels models;

	@ModifyVariable(method = "getHeldItemModel", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/render/item/ItemModels;getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;"))
	private BakedModel setUmbrellaHeldModel(BakedModel model, ItemStack stack) {
		return stack.getItem() == Items.UMBRELLA ? this.models.getModelManager().getModel(UmbrellaItem.UMBRELLA_HELD_MODEL) : model;
	}

	@ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
			argsOnly = true, at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/util/math/MatrixStack;push()V"))
	private BakedModel setUmbrellaIconModel(BakedModel model, ItemStack stack, ModelTransformation.Mode renderMode) {
		boolean shouldUseIcon = renderMode == ModelTransformation.Mode.GUI ||
								renderMode == ModelTransformation.Mode.GROUND ||
								renderMode == ModelTransformation.Mode.FIXED;

		return stack.getItem() == Items.UMBRELLA && shouldUseIcon
				? this.models.getModelManager().getModel(UmbrellaItem.UMBRELLA_ICON_MODEL) : model;
	}

	@ModifyVariable(method = "renderGuiItemModel", at = @At(value = "STORE", ordinal = 0))
	private boolean fixUmbrellaGuiDepthLighting(boolean original, ItemStack stack) {
		return original || stack.getItem() == Items.UMBRELLA;
	}
}
