package artifacts.mixins.item.kittyslippers;

import artifacts.common.init.Items;
import artifacts.common.trinkets.TrinketsHelper;
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
public abstract class MixinFollowTargetGoal<T extends LivingEntity> extends TrackTargetGoal {

	@Shadow @Final protected Class<T> targetClass;
	@Unique private static final Predicate<LivingEntity> NOT_WEARING_KITTY_SLIPPERS = entity -> !TrinketsHelper.isEquipped(Items.KITTY_SLIPPERS, entity);

	public MixinFollowTargetGoal(MobEntity mob, boolean checkVisibility) {
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
