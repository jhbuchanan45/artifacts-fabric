package artifacts.mixin.mixins.item.whoopeecushion;

import artifacts.init.Items;
import artifacts.init.SoundEvents;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "setSneaking", at = @At("RETURN"))
	private void playFartSound(boolean sneaking, CallbackInfo info) {
		Entity entity = (Entity) (Object) this;

		//noinspection ConstantConditions
		if (sneaking && !entity.world.isClient() && entity instanceof LivingEntity
				&& TrinketsHelper.isEquipped(Items.WHOOPEE_CUSHION, (LivingEntity) entity)
				&& ((LivingEntity) entity).getRandom().nextInt(8) == 0) {
			entity.world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
					SoundEvents.FART, SoundCategory.PLAYERS, 1,
					0.9F + ((LivingEntity) entity).getRandom().nextFloat() * 0.2F);
		}
	}
}
