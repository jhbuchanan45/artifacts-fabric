package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.HeliumFlamingoModel;
import artifacts.init.Components;
import artifacts.init.Items;
import artifacts.init.SoundEvents;
import artifacts.trinkets.TrinketsHelper;
import be.florens.expandability.api.fabric.PlayerSwimCallback;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Language;


public class HeliumFlamingoItem extends TrinketArtifactItem {

	public static final Identifier C2S_AIR_SWIMMING_ID = Artifacts.id("c2s_air_swimming");
	private static final Identifier TEXTURE = new Identifier(Artifacts.MODID, "textures/entity/trinket/helium_flamingo.png");

	public HeliumFlamingoItem() {
		super();
		PlayerSwimCallback.EVENT.register(HeliumFlamingoItem::onPlayerSwim);
		ServerPlayNetworking.registerGlobalReceiver(C2S_AIR_SWIMMING_ID, HeliumFlamingoItem::handleAirSwimmingPacket);
	}

	private static ActionResult onPlayerSwim(PlayerEntity player) {
		return TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, player) ? ActionResult.SUCCESS : ActionResult.PASS;
	}

	private static void handleAirSwimmingPacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		boolean enabled = buf.readBoolean();

		server.execute(() -> {
			Components.SWIM_ABILITIES.get(player).setAbility(Artifacts.id("air_swimming"), enabled);
		});
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return new HeliumFlamingoModel();
	}

	@Override
	protected Identifier getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.POP, 1f, 0.7f);
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
