package artifacts.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("UnstableApiUsage")
public class ArtifactAbilitiesComponent implements PlayerComponent<Component>, AutoSyncedComponent {

	private final Map<Identifier, Boolean> abilities = new HashMap<>();
	private final PlayerEntity player;

	public ArtifactAbilitiesComponent(PlayerEntity player) {
		this.player = player;
	}

	public boolean getAbility(Identifier ability) {
		return this.abilities.get(ability);
	}

	public void setAbility(Identifier id, boolean value) {
		this.abilities.put(id, value);
	}

	public void toggleAbility(Identifier id) {
		this.abilities.put(id, !this.abilities.get(id));
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		NbtCompound booleanTags = tag.getCompound("artifactAbilities");
		for (String id : booleanTags.getKeys()) {
			this.abilities.put(new Identifier(id), booleanTags.getBoolean(id));
		}
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		NbtCompound booleanTags = new NbtCompound();
		this.abilities.forEach((id, value) -> {
			booleanTags.putBoolean(id.toString(), value);
		});

		tag.put("artifactAbilities", booleanTags);
	}

	@Override
	public boolean shouldSyncWith(ServerPlayerEntity player) {
		return player.getUuid().equals(this.player.getUuid());
	}
}
