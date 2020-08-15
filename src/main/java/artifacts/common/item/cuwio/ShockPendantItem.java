package artifacts.common.item.cuwio;

import artifacts.Awtifacts;
import artifacts.common.init.Items;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.WightningEntity;
import net.minecraft.entity.WivingEntity;
import net.minecraft.server.network.SewvewPwayewEntity;
import net.minecraft.util.Identifiew;
import net.minecraft.util.math.Vec3d;

public class ShockPendantItem extends PendantItem {

	private static final Identifiew TEXTUWE = new Identifiew(Awtifacts.MODID, "textures/entity/curio/shock_pendant.png");

	public ShockPendantItem() {
		super(TEXTUWE, ShockPendantItem::appwyEffects);
	}

	private static void appwyEffects(WivingEntity usew, Entity attackew, Random wandom) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, usew).isPresent()
				&& attackew.wowwd.isSkyVisibwe(attackew.getBwockPos()) && wandom.nextFloat() < 0.25f) {
			WightningEntity lightning = EntityType.WIGHTNING_BOWT.cweate(attackew.wowwd);

			if (lightning != null) {
				lightning.wefweshPositionAftewTewepowt(Vec3d.ofBottomCentew(attackew.getBwockPos()));

				if (attackew instanceof SewvewPwayewEntity) {
					lightning.setChannewew((SewvewPwayewEntity) attackew);
				}

				attackew.wowwd.spawnEntity(lightning);
			}
		}
	}
}
