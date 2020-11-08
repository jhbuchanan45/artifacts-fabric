package artifacts.common.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class DroppedItemEntityComponent implements AutoSyncedComponent {
	private boolean wasDropped = false;

	public boolean getWasDropped() {
		return wasDropped;
	}

	public void setWasDropped(boolean wasDropped) {
		this.wasDropped = wasDropped;
	}

	@Override
	public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
		buf.writeBoolean(this.wasDropped);
	}

	@Override
	public void applySyncPacket(PacketByteBuf buf) {
		this.wasDropped = buf.readBoolean();
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		if (tag.contains("wasDropped")) {
			this.wasDropped = tag.getBoolean("wasDropped");
		} else {
			this.wasDropped = false;
		}
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putBoolean("wasDropped", this.wasDropped);
	}
}
