package artifacts.common.item.cuwio;

import artifacts.common.item.AwtifactItem;
import artifacts.common.item.Cuwio;
import nerdhub.cardinal.components.api.event.ItemComponentCallbackV2;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.CuriosComponent;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public abstract class CuwioAwtifactItem extends AwtifactItem {

	public CuwioAwtifactItem(Settings settings) {
		super(settings);

		// Setup Curio for item
		ItemComponentCallbackV2.event(this).register((item, stack, componentContainer) -> {
			componentContainer.put(CuriosComponent.ITEM, attachCuwio(stack));

			if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
				componentContainer.put(CuriosComponent.ITEM_RENDER, attachWendewabweCuwio(stack));
			}
		});
	}

	protected ICurio attachCuwio(ItemStack stack) {
		return new Cuwio(this);
	}

	protected abstract IRenderableCurio attachWendewabweCuwio(ItemStack stack);
}
