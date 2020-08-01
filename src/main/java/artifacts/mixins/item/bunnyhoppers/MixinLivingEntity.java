package artifacts.mixins.item.bunnyhoppers;

import artifacts.common.extensions.LivingEntityExtension;
import artifacts.common.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity implements LivingEntityExtension {

	public MixinLivingEntity(EntityType<?> type, World world) {
		super(type, world);
	}

    @Shadow protected abstract float getSoundVolume();

    @Shadow protected abstract float getSoundPitch();

	@Inject(method = "playHurtSound", at = @At("HEAD"))
	private void onServerPlayHurtSound(DamageSource source, CallbackInfo info) {
		artifacts$playBunnyHoppersHurtSound();
	}

	/**
	 * Play rabbit hurt sound when wearing bunny hoppers
	 * Plays alongside default hurt sound
	 */
	// TODO: revisit this playSound mess (see https://fabricmc.net/wiki/tutorial:sounds)
	public void artifacts$playBunnyHoppersHurtSound() {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.BUNNY_HOPPERS, (LivingEntity) (Object) this).isPresent()) {
			this.playSound(SoundEvents.ENTITY_RABBIT_HURT, this.getSoundVolume(), this.getSoundPitch());
		}
	}
}
