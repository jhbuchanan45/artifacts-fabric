package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.GloveModel;
import artifacts.events.LivingEntityDamagedCallback;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Identifier;

public class VampiricGloveItem extends GloveItem {

	private static final Identifier TEXTURE_DEFAULT = new Identifier(Artifacts.MODID, "textures/entity/trinket/vampiric_glove_default.png");
	private static final Identifier TEXTURE_SLIM = new Identifier(Artifacts.MODID, "textures/entity/trinket/vampiric_glove_slim.png");

	public VampiricGloveItem() {
		LivingEntityDamagedCallback.EVENT.register(VampiricGloveItem::onLivingDamage);
	}

	private static void onLivingDamage(LivingEntity entity, DamageSource source, float amount) {
		if (source.getAttacker() instanceof LivingEntity) {
			Entity src = source.getSource();
			LivingEntity attacker = (LivingEntity) source.getAttacker();
			float damageDealt = Math.min(amount, entity.getHealth());
			if (src == attacker && damageDealt > 4 && TrinketsHelper.isEquipped(Items.VAMPIRIC_GLOVE, attacker)) {
				attacker.heal(Math.min(2, amount / 4));
			}
		}
	}

	@Override
	protected Identifier getTexture() {
		return TEXTURE_DEFAULT;
	}

	@Override
	protected Identifier getSlimTexture() {
		return TEXTURE_SLIM;
	}
}
