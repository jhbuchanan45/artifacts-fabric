package artifacts.mixins.item.diggingclaws;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {

    @Shadow @Final public PlayerInventory inventory;

	protected MixinPlayerEntity(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	/**
	 * Sets the holder on the itemstack to check
	 */
	@Inject(method = "isUsingEffectiveTool", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEffectiveOn(Lnet/minecraft/block/BlockState;)Z"))
	private void setItemStackHolding(BlockState block, CallbackInfoReturnable<Boolean> info) {
		this.inventory.getMainHandStack().setHolder(this);
	}
}
