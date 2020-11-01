package artifacts.mixins.statuseffect;

import artifacts.common.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

	@Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

	public MixinLivingEntity(EntityType<?> type, World world) {
		super(type, world);
	}

	/**
	 * Applies permanent status effects added by trinkets every 15 ticks
	 */
	@Inject(method = "tick", at = @At("TAIL"))
	private void applyPermanentEffects(CallbackInfo info) {
		//noinspection ConstantConditions
		if (!this.world.isClient && this.age % 15 == 0 && (Object) this instanceof PlayerEntity) {
			Inventory inventory = TrinketsApi.getTrinketsInventory((PlayerEntity)(Object) this);

			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.getStack(i).getItem();

				if (item instanceof TrinketArtifactItem) {
					StatusEffectInstance effect = ((TrinketArtifactItem) item).getPermanentEffect();

					if (effect != null) {
						this.addStatusEffect(effect);
					}
				}
			}
		}
	}
}
