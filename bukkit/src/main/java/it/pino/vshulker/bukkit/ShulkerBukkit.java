package it.pino.vshulker.bukkit;

import it.pino.vshulker.api.ShulkerAPI;
import it.pino.vshulker.api.listener.ListenerService;
import it.pino.vshulker.api.snapshot.SnapshotService;
import it.pino.vshulker.bukkit.listener.ShulkerListenerService;
import it.pino.vshulker.bukkit.snapshot.ShulkerSnapshotService;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

final class ShulkerBukkit implements ShulkerAPI {

    @NotNull
    private final JavaPlugin plugin;
    @NotNull
    private final SnapshotService snapshotService;
    @NotNull
    private final ListenerService listenerService;

    ShulkerBukkit(@NotNull final JavaPlugin plugin) {
        this.plugin = plugin;
        this.snapshotService = new ShulkerSnapshotService(plugin);
        this.listenerService = new ShulkerListenerService(this);
    }

    void load() {
        this.listenerService.registerListeners(plugin);
    }

    void unload() {
        this.listenerService.unregisterListeners(plugin);
    }

    @Override
    public @NotNull JavaPlugin getPlugin() {
        return this.plugin;
    }

    @Override
    public @NotNull SnapshotService getSnapshotService() {
        return this.snapshotService;
    }
}
