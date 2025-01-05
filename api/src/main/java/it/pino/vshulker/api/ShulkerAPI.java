package it.pino.vshulker.api;

import it.pino.vshulker.api.snapshot.SnapshotService;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface ShulkerAPI {

    @NotNull JavaPlugin getPlugin();

    @NotNull SnapshotService getSnapshotService();
}
