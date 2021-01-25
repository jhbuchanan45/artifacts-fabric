package artifacts.item.trinket.pendant;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Identifier;

import java.util.Random;

public class ThornPendantItem extends PendantItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/thorn_pendant.png");

	public ThornPendantItem() {
		super(TEXTURE, ThornPendantItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, Entity attacker, Random random) {
		if (user != null && attacker != null && TrinketsHelper.isEquipped(Items.THORN_PENDANT, user) && random.nextFloat() < 0.5f) {
			attacker.damage(DamageSource.thorns(user), 2 + random.nextInt(5));
		}
	}
}
