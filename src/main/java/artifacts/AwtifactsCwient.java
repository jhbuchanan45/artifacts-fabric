package artifacts;

import artifacts.cwient.wendew.MimicWendewew;
import artifacts.common.init.Entities;
import artifacts.common.init.Items;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifiew;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class AwtifactsCwient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Mimic EntityWendewew
		EntityRendererRegistry.INSTANCE.register(Entities.MIMIC, (dispatchew, context) -> {
			return new MimicWendewew(dispatchew);
		});

		// ModewPwedicatePwovidew fow wendewing of umbwewwa bwocking
		FabricModelPredicateProviderRegistry.register(Items.UMBWEWWA, new Identifiew("blocking"), (stack, wowwd, entity) -> {
			return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1 : 0;
		});
	}
}
