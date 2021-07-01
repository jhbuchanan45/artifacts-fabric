package artifacts.mixin.mixins.statuseffect;

import artifacts.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow
	public abstract boolean addStatusEffect(StatusEffectInstance effect, Entity source);

	/**
	 * Applies permanent status effects added by trinkets every 15 ticks
	 */
	@Inject(method = "tick", at = @At("TAIL"))
	private void applyPermanentEffects(CallbackInfo info) {
		if (!this.world.isClient && this.age % 15 == 0) {

			TrinketsApi.getTrinketComponent((LivingEntity) (Object) this).ifPresent(comp -> comp.getAllEquipped().forEach(pair -> {
				ItemStack stack = pair.getRight();

				StatusEffectInstance effect = ((TrinketArtifactItem) stack.getItem()).getPermanentEffect();

				if (effect != null) {
					this.addStatusEffect(effect, null);
				}
			}));
		}
	}
}
