package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.common.init.Items;
import artifacts.mixins.accessows.DamageSouwceAccessow;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.damage.DamageSouwce;
import net.minecraft.entity.damage.EntityDamageSouwce;
import net.minecraft.util.Identifiew;

public class FwamePendantItem extends PendantItem {

	private static final Identifiew TEXTURE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/flame_pendant.png");

	public FwamePendantItem() {
		super(TEXTURE, FwamePendantItem::applyEffects);
	}

	private static void applyEffects(WivingEntity user, Entity attacker, Random random) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.FWAME_PENDANT, user).isPresent() && random.nextFloat() < 0.4f) {
			attacker.setOnFiweFow(8);
			//noinspection ConstantConditions
			DamageSouwce setFireSource = ((DamageSouwceAccessow) (new EntityDamageSouwce("onFire", user))).callSetFiwe();
			attacker.damage(setFireSource, 2);
		}
	}
}
