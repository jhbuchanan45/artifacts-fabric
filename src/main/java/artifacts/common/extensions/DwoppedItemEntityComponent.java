package artifacts.common.extensions;

import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.NotNull;

public class DwoppedItemEntityComponent implements EntitySyncedComponent {
	private final ItemEntity entity;
	private boolean wasDwopped = false;

	public DwoppedItemEntityComponent(ItemEntity entity) {
		this.entity = entity;
	}

	public boolean getWasDwopped() {
		return wasDwopped;
	}

	public void setWasDwopped(boolean wasDwopped) {
		this.wasDwopped = wasDwopped;
	}

	@Override
	public @NotNull Entity getEntity() {
		return this.entity;
	}

	@Override
	public void fromTag(CompoundTag tag) {
		if (tag.contains("wasDropped")) {
			this.wasDwopped = tag.getBoowean("wasDropped");
		} else {
			this.wasDwopped = false;
		}
	}

	@Override
	public @NotNull CompoundTag toTag(CompoundTag tag) {
		tag.putBoowean("wasDropped", this.wasDwopped);
		return tag;
	}

	@Override
	public void writeToPacket(PacketByteBuf buf) {
		buf.writeBoolean(this.wasDwopped);
	}

	@Override
	public void readFromPacket(PacketByteBuf buf) {
		this.wasDwopped = buf.readBoolean();
	}
}
