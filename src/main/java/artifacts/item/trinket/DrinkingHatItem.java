package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CrystalHeartModel;
import artifacts.client.render.model.trinket.DrinkingHatModel;
import artifacts.init.Items;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class DrinkingHatItem extends TrinketArtifactItem {

	private final Identifier texture;

	public DrinkingHatItem(Identifier texture) {
		this.texture = texture;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flags) {
		if (Artifacts.CONFIG.general.showTooltips) {
			if (this == Items.NOVELTY_DRINKING_HAT) {
				// Novelty drinking hat description is the same as plastic, but with an extra line appended
				appendTooltipDescription(tooltip, Items.PLASTIC_DRINKING_HAT.getTranslationKey() + ".tooltip");
			}
		}
		super.appendTooltip(stack, world, tooltip, flags);
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_BOTTLE_FILL;
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(DrinkingHatModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new DrinkingHatModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return texture;
	}
}
