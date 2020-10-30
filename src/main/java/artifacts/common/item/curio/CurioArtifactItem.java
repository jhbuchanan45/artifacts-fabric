package artifacts.common.item.curio;

import artifacts.common.item.ArtifactItem;
import artifacts.common.item.Curio;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
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
	}

	public ICurio attachCurio(ItemStack stack) {
		return new Curio(this);
	}

	public abstract IRenderableCurio attachRenderableCurio(ItemStack stack);
}
