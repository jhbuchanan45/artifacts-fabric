package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.FlippersModel;
import artifacts.client.render.model.trinket.HeliumFlamingoModel;
import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.init.SoundEvents;
import be.florens.expandability.api.fabric.PlayerSwimCallback;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;


public class HeliumFlamingoItem extends TrinketArtifactItem {

	public static final Identifier C2S_AIR_SWIMMING_ID = Artifacts.id("c2s_air_swimming");
	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/helium_flamingo.png");

	public HeliumFlamingoItem() {
		super();
		PlayerSwimCallback.EVENT.register(HeliumFlamingoItem::onPlayerSwim);
		ServerPlayNetworking.registerGlobalReceiver(C2S_AIR_SWIMMING_ID, HeliumFlamingoItem::handleAirSwimmingPacket);
	}

	private static ActionResult onPlayerSwim(PlayerEntity player) {
		SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(player);

		if (swimAbilities.isSwimming()) {
			return ActionResult.SUCCESS;
		}

		return ActionResult.PASS;
	}

	private static void handleAirSwimmingPacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		boolean shouldSwim = buf.readBoolean();
		server.execute(() -> Components.SWIM_ABILITIES.get(player).setSwimming(shouldSwim));
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(HeliumFlamingoModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new HeliumFlamingoModel(root);
	}

	@Override
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.POP;
	}

	@Override
	protected Object[] getTooltipDescriptionArguments() {
		String translationKey = MinecraftClient.getInstance().options.keySprint.getBoundKeyTranslationKey();
		return new Object[] {Language.getInstance().get(translationKey)};
	}

/*	public static boolean isFlying(LivingEntity entity) {
		return TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, entity)
				&& entity instanceof PlayerEntity
				&& Components.SWIM_ABILITIES.get((PlayerEntity) entity).isAirSwimming();
	}*/
}
