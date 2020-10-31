package artifacts.common.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.BunnyHoppersModel;
import artifacts.common.events.PlayHurtSoundCallback;
import artifacts.common.init.Items;
import artifacts.common.item.Curio;
import artifacts.common.item.RenderableCurio;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.component.ICurio;
import top.theillusivec4.curios.api.type.component.IRenderableCurio;

public class BunnyHoppersItem extends TrinketArtifactItem {

	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/curio/bunny_hoppers.png");
	private Object model;

	public BunnyHoppersItem() {
		super(new Item.Settings());
		PlayHurtSoundCallback.EVENT.register(BunnyHoppersItem::onPlayHurtSound);
	}

	private static void onPlayHurtSound(LivingEntity entity, float volume, float pitch) {
		CuriosApi.getCuriosHelper().findEquippedCurio(Items.BUNNY_HOPPERS, entity).ifPresent(curio -> {
			entity.playSound(SoundEvents.ENTITY_RABBIT_HURT, volume, pitch);
		});
	}

	@Override
	public ICurio attachCurio(ItemStack stack) {
		return new Curio(this) {
			@Override
			public StatusEffectInstance getPermanentEffect() {
				return new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20, 1, true, false);
			}
		};
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BunnyHoppersModel getModel() {
		if (model == null) {
			model = new BunnyHoppersModel();
		}
		return (BunnyHoppersModel) model;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return false;
	}
}
