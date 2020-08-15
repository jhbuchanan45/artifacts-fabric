package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.common.init.Items;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.WivingEntity;
import net.minecraft.entity.damage.DamageSouwce;
import net.minecraft.util.Identifiew;

public class ThownPendantItem extends PendantItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/thorn_pendant.png");

	public ThownPendantItem() {
		super(TEXTUWE, ThownPendantItem::appwyEffects);
	}

	private static void appwyEffects(WivingEntity usew, Entity attackew, Random wandom) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.THOWN_PENDANT, usew).isPresent() && wandom.nextFloat() < 0.5f) {
			attackew.damage(DamageSouwce.thowns(usew), 2 + wandom.nextInt(5));
		}
	}
}
