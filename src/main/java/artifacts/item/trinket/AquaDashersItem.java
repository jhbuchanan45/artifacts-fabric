package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.AquaDashersModel;
import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.trinkets.Slots;
import be.florens.expandability.api.fabric.LivingFluidCollisionCallback;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;

public class AquaDashersItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/aqua_dashers.png");

	public AquaDashersItem() {
		//noinspection UnstableApiUsage
		LivingFluidCollisionCallback.EVENT.register(AquaDashersItem::onFluidCollision);
	}

	private static boolean onFluidCollision(LivingEntity entity, FluidState fluidState) {
		if (entity.isSprinting() && entity.fallDistance < 6 && !entity.isUsingItem() && !entity.isInSneakingPose()) {
			SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(entity);

			if (!swimAbilities.isWet() && !swimAbilities.isSwimming()) {
				if (fluidState.isIn((FluidTags.LAVA))) {
					if (!entity.isFireImmune() && !EnchantmentHelper.hasFrostWalker(entity)) {
						entity.damage(DamageSource.HOT_FLOOR, 1);
					}
				}
				return true;
			}
		}

		return false;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new AquaDashersModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
