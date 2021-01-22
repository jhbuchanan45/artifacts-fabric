package artifacts.mixin.mixins.statuseffect;

import artifacts.common.item.trinket.TrinketArtifactItem;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

	public MixinLivingEntity(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow
	public abstract boolean addStatusEffect(StatusEffectInstance effect);

	/**
	 * Applies permanent status effects added by trinkets every 15 ticks
	 */
	@Inject(method = "tick", at = @At("TAIL"))
	private void applyPermanentEffects(CallbackInfo info) {
		//noinspection ConstantConditions
		if (!this.world.isClient && this.age % 15 == 0 && (Object) this instanceof LivingEntity) {

			TrinketsHelper.getAllEquipped((LivingEntity) (Object) this).forEach(stack -> {
				StatusEffectInstance effect = ((TrinketArtifactItem) stack.getItem()).getPermanentEffect();

				if (effect != null) {
					this.addStatusEffect(effect);
				}
			});
		}
	}
}
