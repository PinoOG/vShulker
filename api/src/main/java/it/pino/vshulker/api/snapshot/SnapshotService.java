package it.pino.vshulker.api.snapshot;

import org.bukkit.NamespacedKey;
import org.bukkit.block.ShulkerBox;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SnapshotService {

    @NotNull Map<UUID, Snapshot> getSnapshots();

    NamespacedKey getKey();

    Optional<Snapshot> getSnapshot(final @NotNull UUID owner);

    void addSnapshot(@NotNull final UUID owner, @NotNull final ShulkerBox shulker, @NotNull final UUID identifier);
}
