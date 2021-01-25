package artifacts.mixin.mixins.trinkets;

import artifacts.item.trinket.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerEntity.class, priority = 900)
public abstract class PlayerEntityMixin extends LivingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	/**
	 * Prevent player from equipping two of the same Artifacts trinkets
	 */
	@Inject(method = "canEquip(Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
	public void canEquipNoDuplicate(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
		if (stack.getItem() instanceof TrinketArtifactItem && TrinketsHelper.isEquipped(stack.getItem(), this)) {
			info.setReturnValue(false);
		}
	}
}
