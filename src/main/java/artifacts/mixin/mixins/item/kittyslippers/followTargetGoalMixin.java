package artifacts.mixin.mixins.item.kittyslippers;

import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

@Mixin(FollowTargetGoal.class)
public abstract class followTargetGoalMixin<T extends LivingEntity> extends TrackTargetGoal {

	@Unique
	private static final Predicate<LivingEntity> NOT_WEARING_KITTY_SLIPPERS = entity -> !TrinketsHelper.isEquipped(Items.KITTY_SLIPPERS, entity);
	@Shadow
	@Final
	protected Class<T> targetClass;

	public followTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
		super(mob, checkVisibility);
	}

	/**
	 * Makes it so creepers don't follow players with kitty slippers
	 */
	@ModifyArg(method = "<init>(Lnet/minecraft/entity/mob/MobEntity;Ljava/lang/Class;IZZLjava/util/function/Predicate;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/TargetPredicate;setPredicate(Ljava/util/function/Predicate;)Lnet/minecraft/entity/ai/TargetPredicate;"))
	private @Nullable Predicate<LivingEntity> addCreeperTargetPredicate(@Nullable Predicate<LivingEntity> targetPredicate) {
		if (this.mob.getType() == EntityType.CREEPER && this.targetClass == PlayerEntity.class) {
			return targetPredicate == null ? NOT_WEARING_KITTY_SLIPPERS : targetPredicate.and(NOT_WEARING_KITTY_SLIPPERS);
		}
		return targetPredicate;
	}
}
