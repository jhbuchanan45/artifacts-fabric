package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.common.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Random;

public class ThornPendantItem extends PendantItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/thorn_pendant.png");

	public ThornPendantItem() {
		super(TEXTURE, ThornPendantItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, Entity attacker, Random random) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.THORN_PENDANT, user).isPresent() && random.nextFloat() < 0.5f) {
			attacker.damage(DamageSource.thorns(user), 2 + random.nextInt(5));
		}
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return false;
	}
}
