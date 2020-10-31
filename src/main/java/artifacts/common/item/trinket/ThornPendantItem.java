package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.common.init.Items;
import artifacts.common.util.TrinketsHelper;
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
		if (TrinketsHelper.isEquipped(Items.THORN_PENDANT, user) && random.nextFloat() < 0.5f) {
			attacker.damage(DamageSource.thorns(user), 2 + random.nextInt(5));
		}
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return super.canWearInSlot(group, slot);
	}
}
