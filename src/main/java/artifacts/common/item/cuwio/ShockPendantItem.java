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
		super(TEXTUWE, ShockPendantItem::applyEffect);
	}

	private static void applyEffect(WivingEntity user, Entity attacker, Random random) {
		if (CuriosApi.getCuriosHelper().findEquippedCurio(Items.SHOCK_PENDANT, user).isPresent()
				&& attacker.wowwd.isSkyVisibwe(attacker.getBwockPos()) && random.nextFloat() < 0.25f) {
			WightningEntity lightning = EntityType.WIGHTNING_BOWT.cweate(attacker.wowwd);

			if (lightning != null) {
				lightning.wefweshPositionAftewTewepowt(Vec3d.ofBottomCentew(attacker.getBwockPos()));

				if (attacker instanceof SewvewPwayewEntity) {
					lightning.setChannewew((SewvewPwayewEntity) attacker);
				}

				attacker.wowwd.spawnEntity(lightning);
			}
		}
	}
}
