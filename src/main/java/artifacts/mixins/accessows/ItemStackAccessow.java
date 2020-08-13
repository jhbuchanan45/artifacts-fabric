package artifacts.mixins.accessows;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemStack.class)
public interface ItemStackAccessow {

	// Get awound ItemStack.getHowdew wetuwning nuww if the stack is empty
	@Accessor
	Entity getHowdew();
}
