package artifacts.mixins.item.pendant;

import artifacts.common.init.Items;
import artifacts.mixins.accessors.DamageSourceAccessor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Random;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {

    @Inject(method = "onUserDamaged", at = @At("HEAD"))
    private static void applyPendantEffect(LivingEntity user, Entity attacker, CallbackInfo info) {
        Random random = user.getRandom();

        if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.THORN_PENDANT, user).isPresent() && random.nextFloat() < 0.5f) {
            attacker.damage(DamageSource.thorns(user), 2 + random.nextInt(5));
        }

        if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.FLAME_PENDANT, user).isPresent() && random.nextFloat() < 0.4f) {
            attacker.setOnFireFor(8);
            //noinspection ConstantConditions
            DamageSource setFireSource = ((DamageSourceAccessor) (new EntityDamageSource("onFire", user))).callSetFire();
            attacker.damage(setFireSource, 2);
        }

        if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, user).isPresent()
                && attacker.world.isSkyVisible(attacker.getBlockPos()) && random.nextFloat() < 0.25f) {
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(attacker.world);

            if (lightning != null) {
                lightning.method_29495(Vec3d.ofBottomCenter(attacker.getBlockPos()));

                if (attacker instanceof ServerPlayerEntity) {
                    lightning.setChanneler((ServerPlayerEntity) attacker);
                }

                attacker.world.spawnEntity(lightning);
            }
        }
    }
}
