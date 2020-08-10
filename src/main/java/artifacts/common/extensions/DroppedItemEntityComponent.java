package artifacts.common.extensions;

import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.NotNull;

public class DroppedItemEntityComponent implements EntitySyncedComponent {
	private final ItemEntity entity;
	private boolean wasDropped = false;

	public DroppedItemEntityComponent(ItemEntity entity) {
		this.entity = entity;
	}

	public boolean getWasDropped() {
		return wasDropped;
	}

	public void setWasDropped(boolean wasDropped) {
		this.wasDropped = wasDropped;
	}

	@Override
	public @NotNull Entity getEntity() {
		return this.entity;
	}

	@Override
	public void fromTag(CompoundTag tag) {
		if (tag.contains("wasDropped")) {
			this.wasDropped = tag.getBoolean("wasDropped");
		} else {
			this.wasDropped = false;
		}
	}

	@Override
	public @NotNull CompoundTag toTag(CompoundTag tag) {
		tag.putBoolean("wasDropped", this.wasDropped);
		return tag;
	}

	@Override
	public void writeToPacket(PacketByteBuf buf) {
		buf.writeBoolean(this.wasDropped);
	}

	@Override
	public void readFromPacket(PacketByteBuf buf) {
		this.wasDropped = buf.readBoolean();
	}
}
