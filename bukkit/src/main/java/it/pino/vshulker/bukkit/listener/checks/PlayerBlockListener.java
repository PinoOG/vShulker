package it.pino.vshulker.bukkit.listener.checks;

import it.pino.vshulker.api.listener.Listener;
import it.pino.vshulker.api.snapshot.Snapshot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public final class PlayerBlockListener implements Listener<BlockPlaceEvent> {


    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(@NotNull final BlockPlaceEvent event) {
        if(event.isCancelled()) return;
        var player = event.getPlayer();

        if(!(player.getOpenInventory().getTopInventory().getHolder(false) instanceof Snapshot)) return;

        event.setCancelled(true);

    }
}
