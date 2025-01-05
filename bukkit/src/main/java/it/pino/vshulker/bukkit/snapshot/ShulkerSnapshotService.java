package it.pino.vshulker.bukkit.snapshot;

import it.pino.vshulker.api.snapshot.SnapshotService;
import it.pino.vshulker.api.snapshot.Snapshot;
import org.bukkit.NamespacedKey;
import org.bukkit.block.ShulkerBox;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ShulkerSnapshotService implements SnapshotService {

    @NotNull
    private final Map<UUID, Snapshot> snapshots;
    @NotNull
    private final NamespacedKey key;

    public ShulkerSnapshotService(@NotNull final JavaPlugin plugin) {
        this.snapshots = new ConcurrentHashMap<>();
        this.key = new NamespacedKey(plugin, "shulker-key");
    }

    @Override
    public @NotNull Map<UUID, Snapshot> getSnapshots() {
        return Collections.unmodifiableMap(this.snapshots);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.key;
    }

    @Override
    public Optional<Snapshot> getSnapshot(@NotNull final UUID owner) {
        return Optional.ofNullable(this.snapshots.get(owner));
    }

    @Override
    public void addSnapshot(@NotNull final UUID owner, @NotNull final ShulkerBox shulker, @NotNull final UUID identifier) {
        var contents = shulker.getInventory().getContents().clone();
        var snapshot = new ShulkerSnapshot(contents, identifier);
        this.snapshots.put(owner, snapshot);
    }
}
