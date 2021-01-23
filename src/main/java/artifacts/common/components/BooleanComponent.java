package artifacts.common.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class BooleanComponent implements AutoSyncedComponent {

	private final String name;
	private boolean bool;

	public BooleanComponent(String name) {
		this.name = name;
	}

	public boolean get() {
		return bool;
	}

	public void set(boolean bool) {
		this.bool = bool;
	}

	public void invert() {
		this.bool = !this.bool;
	}

	@Override
	public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
		buf.writeBoolean(this.bool);
	}

	@Override
	public void applySyncPacket(PacketByteBuf buf) {
		this.bool = buf.readBoolean();
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		if (tag.contains(this.name)) {
			this.bool = tag.getBoolean(this.name);
		} else {
			this.bool = false;
		}
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putBoolean(this.name, this.bool);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof BooleanComponent) {
			return this.get() == ((BooleanComponent) obj).get();
		}
		return false;
	}
}
