package artifacts.mixins.item.diggingclaws;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Get around ItemStack.getHolder returning null if the stack is empty
 */
@Mixin(ItemStack.class)
public interface ItemStackAccessor {
    @Accessor
    Entity getHolder();
}
