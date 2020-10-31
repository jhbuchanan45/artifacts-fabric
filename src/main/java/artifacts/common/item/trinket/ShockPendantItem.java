package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.common.init.Items;
import artifacts.common.util.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class ShockPendantItem extends PendantItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/shock_pendant.png");

	public ShockPendantItem() {
		super(TEXTURE, ShockPendantItem::applyEffect);
	}

	private static void applyEffect(LivingEntity user, Entity attacker, Random random) {
		if (TrinketsHelper.isEquipped(Items.SHOCK_PENDANT, user) && attacker.world.isSkyVisible(attacker.getBlockPos())
				&& random.nextFloat() < 0.25f) {
			LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(attacker.world);

			if (lightning != null) {
				lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(attacker.getBlockPos()));

				if (attacker instanceof ServerPlayerEntity) {
					lightning.setChanneler((ServerPlayerEntity) attacker);
				}

				attacker.world.spawnEntity(lightning);
			}
		}
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return super.canWearInSlot(group, slot);
	}
}
