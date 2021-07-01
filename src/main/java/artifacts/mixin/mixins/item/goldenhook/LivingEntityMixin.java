package artifacts.mixin.mixins.item.goldenhook;

import artifacts.init.Components;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	@Shadow @Nullable protected PlayerEntity attackingPlayer;

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@ModifyArg(method = "dropXp", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"))
	private int modifyXp(int originalXp) {
		if (!TrinketsHelper.isEquipped(Items.GOLDEN_HOOK, this.attackingPlayer)) {
			return originalXp;
		}

		double killRatio = Components.ENTITY_KILL_TRACKER.maybeGet(this.attackingPlayer)
				.map(comp -> comp.getKillRatio(this.getType()))
				.orElse(0D);

		// bonus decreases linearly in relation to the ratio kills of the same type in the list of tracked kills
		// no bonus if more than half of the tracked kills are of the same type
		// maximum bonus is 5 * original XP (give or take a few rounding errors)
		double multiplier = 5 * Math.max(0, 2 * ((1 - killRatio) - 1 / 2D));
		int experienceBonus = (int) (multiplier * Math.min(10, originalXp));

		return originalXp + experienceBonus;
	}

	@Inject(method = "onDeath", at = @At("HEAD"))
	private void addToKillTracker(DamageSource source, CallbackInfo info) {
		if (source.getAttacker() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) source.getAttacker();
			Components.ENTITY_KILL_TRACKER.maybeGet(player).ifPresent(comp -> comp.addKilledEntityType(this.getType()));
		}
	}
}
