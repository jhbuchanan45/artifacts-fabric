package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.common.init.Items;
import artifacts.common.util.TrinketsHelper;
import artifacts.mixins.accessors.DamageSourceAccessor;
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
		if (TrinketsHelper.isEquipped(Items.FLAME_PENDANT, user) && random.nextFloat() < 0.4f) {
			attacker.setOnFireFor(8);
			//noinspection ConstantConditions
			DamageSource setFireSource = ((DamageSourceAccessor) (new EntityDamageSource("onFire", user))).callSetFire();
			attacker.damage(setFireSource, 2);
		}
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return super.canWearInSlot(group, slot);
	}
}
