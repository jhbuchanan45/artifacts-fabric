package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.AntidoteVesselModel;
import artifacts.client.render.model.trinket.CloudInABottleModel;
import artifacts.mixin.extensions.LivingEntityExtensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class CloudInABottleItem extends TrinketArtifactItem {

	public static final Identifier C2S_DOUBLE_JUMPED_ID = Artifacts.id("c2s_double_jumped");
	private static final Identifier TEXTURE = Artifacts.id("textures/entity/trinket/cloud_in_a_bottle.png");

	public CloudInABottleItem() {
		ServerPlayNetworking.registerGlobalReceiver(C2S_DOUBLE_JUMPED_ID, CloudInABottleItem::handleDoubleJumpPacket);
	}

	private static void handleDoubleJumpPacket(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		server.execute(() -> {
			((LivingEntityExtensions) player).artifacts$doubleJump();

			// Spawn particles
			for (int i = 0; i < 20; ++i) {
				double motionX = player.getRandom().nextGaussian() * 0.02;
				double motionY = player.getRandom().nextGaussian() * 0.02 + 0.20;
				double motionZ = player.getRandom().nextGaussian() * 0.02;
				player.getServerWorld().spawnParticles(ParticleTypes.POOF, player.getX(), player.getY(), player.getZ(), 1, motionX, motionY, motionZ, 0.15);
			}
		});
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH;
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel() {
		return createModel(CloudInABottleModel.getTexturedModelData().createModel());
	}

	@Environment(EnvType.CLIENT)
	protected BipedEntityModel<LivingEntity> createModel(ModelPart root) {
		return new CloudInABottleModel(root);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected Identifier getTexture() {
		return TEXTURE;
	}
}
