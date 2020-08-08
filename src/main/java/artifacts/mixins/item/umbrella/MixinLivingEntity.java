package artifacts.mixins.item.umbrella;

import artifacts.common.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public abstract ItemStack getMainHandStack();

    @Shadow public abstract ItemStack getOffHandStack();

    public MixinLivingEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyVariable(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;"))
    private double changeGravity(double gravity) {
        boolean isFalling = this.getVelocity().y <= 0.0D;

        if (isFalling && !this.hasStatusEffect(StatusEffects.SLOW_FALLING) && !isTouchingWater() &&
                (this.getMainHandStack().getItem() == Items.UMBRELLA || this.getOffHandStack().getItem() == Items.UMBRELLA)) {
            gravity -= 0.07;
        }

        return gravity;
    }
}
