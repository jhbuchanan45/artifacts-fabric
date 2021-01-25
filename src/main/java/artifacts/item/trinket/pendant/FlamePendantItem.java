package artifacts.item.trinket.pendant;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import artifacts.mixin.mixins.accessors.DamageSourceAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.util.Identifier;

import java.util.Random;

public class FlamePendantItem extends PendantItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/flame_pendant.png");

	public FlamePendantItem() {
		super(TEXTURE, FlamePendantItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, Entity attacker, Random random) {
		if (user != null && attacker != null && TrinketsHelper.isEquipped(Items.FLAME_PENDANT, user) && random.nextFloat() < 0.4f) {
			attacker.setOnFireFor(8);
			//noinspection ConstantConditions
			DamageSource setFireSource = ((DamageSourceAccessor) (new EntityDamageSource("onFire", user))).artifacts$callSetFire();
			attacker.damage(setFireSource, 2);
		}
	}
}
