package artifacts.mixin.mixins.item.kittyslippers;

import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {

	protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	/**
	 * Add flee goal for players with kitty slippers
	 */
	@Inject(method = "initGoals", at = @At("TAIL"))
	private void addPlayerFleeGoal(CallbackInfo info) {
		this.goalSelector.add(3, new FleeEntityGoal<>(this, PlayerEntity.class,
				(entity) -> TrinketsHelper.isEquipped(Items.KITTY_SLIPPERS, entity), 6.0f, 1.0,
				1.2, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR::test));
	}
}
