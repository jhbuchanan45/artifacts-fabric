package artifacts.common.item.curio;

import artifacts.common.item.ArtifactItem;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public abstract class CurioArtifactItem extends ArtifactItem {

	public CurioArtifactItem(Settings settings) {
		super(settings);

		// Setup Curio for item
		ItemComponentCallbackV2.event(this).register((item, stack, componentContainer) -> {
			componentContainer.put(CuriosComponent.ITEM, attachCurio(stack));

			if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
				componentContainer.put(CuriosComponent.ITEM_RENDER, attachRenderableCurio(stack));
			}
		});
	}

	abstract ICurio attachCurio(ItemStack stack);

	abstract IRenderableCurio attachRenderableCurio(ItemStack stack);
}
