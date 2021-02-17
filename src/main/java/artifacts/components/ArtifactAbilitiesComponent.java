package artifacts.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class ArtifactAbilitiesComponent implements AutoSyncedComponent {

	private final PlayerEntity player;
	private boolean isAirSwimming;

	public ArtifactAbilitiesComponent(PlayerEntity player) {
		this.player = player;
	}

	public boolean isAirSwimming() {
		return this.isAirSwimming;
	}

	public void toggleAirSwimming() {
		this.isAirSwimming = !this.isAirSwimming;
	}

	public void setAirSwimming(boolean airSwimming) {
		this.isAirSwimming = airSwimming;
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		this.isAirSwimming = tag.getBoolean("isAirSwimming");
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putBoolean("isAirSwimming", this.isAirSwimming);
	}

	@Override
	public void applySyncPacket(PacketByteBuf buf) {
		this.isAirSwimming = buf.readBoolean();
	}

	@Override
	public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
		buf.writeBoolean(this.isAirSwimming);
	}

	@Override
	public boolean shouldSyncWith(ServerPlayerEntity player) {
		return player.getUuid().equals(this.player.getUuid());
	}
}
