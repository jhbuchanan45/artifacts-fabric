package artifacts.item.trinket;

import artifacts.client.render.model.trinket.DrinkingHatModel;
import artifacts.init.Items;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
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
		if (this != Items.PLASTIC_DRINKING_HAT) {
			appendToolTipLines(tooltip, Items.PLASTIC_DRINKING_HAT.getTranslationKey() + ".tooltip");
		}
		super.appendTooltip(stack, world, tooltip, flags);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new DrinkingHatModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return texture;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.HAT);
	}
}
