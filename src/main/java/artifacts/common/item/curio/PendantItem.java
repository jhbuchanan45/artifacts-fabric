package artifacts.common.item.curio;

import artifacts.client.render.model.curio.PendantModel;
import artifacts.common.events.UserAttackedCallback;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public abstract class PendantItem extends CurioArtifactItem {

	private final Identifier texture;

	public PendantItem(Identifier texture, UserAttackedCallback callback) {
		super(new Item.Settings());
		this.texture = texture;
		UserAttackedCallback.EVENT.register(callback);
	}

	@Override
	protected ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			@Override
			protected SoundEvent getEquipSound() {
				return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
			}
		};
	}

	@Override
	protected IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableCurio() {
			private Object model;

			@Override
			@Environment(EnvType.CLIENT)
			protected PendantModel getModel() {
				if (model == null) {
					model = new PendantModel();
				}
				return (PendantModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return texture;
			}
		};
	}
}
