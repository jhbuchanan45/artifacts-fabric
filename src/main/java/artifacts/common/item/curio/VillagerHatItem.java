package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.VillagerHatModel;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class VillagerHatItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/villager_hat.png");

	public VillagerHatItem() {
		super(new Item.Settings());
	}

	@Override
	public ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {

			@Override
			public StatusEffectInstance getPermanentEffect() {
				return new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 20, 1, true, false);
			}
		};
	}

	@Override
	public IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableCurio() {
			private Object model;

			@Override
			@Environment(EnvType.CLIENT)
			protected VillagerHatModel getModel() {
				if (model == null) {
					model = new VillagerHatModel();
				}
				return (VillagerHatModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return TEXTURE;
			}
		};
	}
}
