package it.pino.vshulker.bukkit.listener;

import it.pino.vshulker.api.listener.ListenerService;
import it.pino.vshulker.api.listener.Listener;
import it.pino.vshulker.api.ShulkerAPI;
import it.pino.vshulker.bukkit.listener.event.InvClickListener;
import it.pino.vshulker.bukkit.listener.event.InvCloseListener;
import it.pino.vshulker.bukkit.listener.event.PlayerDamageListener;
import it.pino.vshulker.bukkit.listener.event.PlayerInteractListener;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public final class ShulkerListenerService implements ListenerService {

    @NotNull
    private final Collection<Listener<?>> listeners;

    public ShulkerListenerService(@NotNull final ShulkerAPI api) {
        this.listeners = List.of(
                new PlayerInteractListener(api.getSnapshotService(), api.getPlugin()),
                new InvClickListener(),
                new InvCloseListener(api.getSnapshotService()),
                new PlayerDamageListener()
        );
    }

    @Override
    public @NotNull Collection<Listener<?>> getRegisterListeners() {
        return this.listeners;
    }
}
