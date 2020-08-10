package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.common.init.Items;
import artifacts.mixins.accessors.DamageSourceAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Random;

public class FlamePendantItem extends PendantItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/flame_pendant.png");

	public FlamePendantItem() {
		super(TEXTURE, FlamePendantItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, Entity attacker, Random random) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.FLAME_PENDANT, user).isPresent() && random.nextFloat() < 0.4f) {
			attacker.setOnFireFor(8);
			//noinspection ConstantConditions
			DamageSource setFireSource = ((DamageSourceAccessor) (new EntityDamageSource("onFire", user))).callSetFire();
			attacker.damage(setFireSource, 2);
		}
	}
}