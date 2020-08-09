package artifacts.common.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.UniversalAttractorModel;
import artifacts.common.init.Components;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

import java.util.List;

public class UniversalAttractorItem extends CurioArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/universal_attractor.png");

	public UniversalAttractorItem() {
		super(new Item.Settings());
	}

	@Override
	protected ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			// magnet logic from Botania, see https://github.com/Vazkii/Botania
			@Override
			public void curioTick(String identifier, int index, LivingEntity entity) {
				if (entity.isSpectator() || !(entity instanceof PlayerEntity)) {
					return;
				}

				Vec3d playerPos = entity.getPos().add(0, 0.75, 0);

				int range = 5;
				List<ItemEntity> items = entity.world.getNonSpectatingEntities(ItemEntity.class, new Box(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
				int pulled = 0;
				for (ItemEntity item : items) {
					if (Components.DROPPED_ITEM_ENTITY.maybeGet(item).isPresent() && Components.DROPPED_ITEM_ENTITY.get(item).getWasDropped() &&
						item.getAge() > 100 && item.isAlive() && !item.cannotPickup()) {
						if (pulled++ > 200) {
							break;
						}

						Vec3d motion = playerPos.subtract(item.getPos().add(0, item.getHeight() / 2, 0));
						if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
							motion = motion.normalize();
						}
						item.setVelocity(motion.multiply(0.6));
					}
				}
			}
		};
	}

	@Override
	protected IRenderableCurio attachRenderableCurio(ItemStack stack) {
		return new RenderableCurio() {
			private Object model;

			@Override
			@Environment(EnvType.CLIENT)
			protected UniversalAttractorModel getModel() {
				if (model == null) {
					model = new UniversalAttractorModel();
				}
				return (UniversalAttractorModel) model;
			}

			@Override
			@Environment(EnvType.CLIENT)
			protected Identifier getTexture() {
				return TEXTURE;
			}
		};
	}
}
