package artifacts.mixin.mixins.accessors;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemStack.class)
public interface ItemStackAccessor {

	// Get around ItemStack.getHolder returning null if the stack is empty
	@Accessor
	Entity getHolder();
}
