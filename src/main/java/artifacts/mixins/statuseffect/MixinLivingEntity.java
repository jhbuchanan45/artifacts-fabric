package artifacts.mixins.statuseffect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

	public MixinLivingEntity(EntityType<?> type, World world) {
		super(type, world);
	}

	/**
	 * Applies permanent status effects added by curios every 15 ticks
	 */
	@Inject(method = "tick", at = @At("TAIL"))
	private void applyPermanentEffects(CallbackInfo info) {
		if (!this.world.isClient && this.age % 15 == 0) {
			// TODO: Port to Trinkets
			/*CuriosApi.getCuriosHelper().getCuriosHandler((LivingEntity)(Object) this).ifPresent(itemHandler -> {

				for (Map.Entry<String, ICurioStacksHandler> entry : itemHandler.getCurios().entrySet()) {
					ICurioStacksHandler stacksHandler = entry.getValue();
					IDynamicStackHandler stacks = stacksHandler.getStacks();

					for (int i = 0; i < stacks.size(); i++) {
						CuriosApi.getCuriosHelper().getCurio(stacks.getStack(i)).ifPresent(curio -> {
							if (curio instanceof Curio && ((Curio) curio).getPermanentEffect() != null) {
								((LivingEntity)(Object) this).addStatusEffect(((Curio) curio).getPermanentEffect());
							}
						});
					}
				}
			});*/
		}
	}
}
