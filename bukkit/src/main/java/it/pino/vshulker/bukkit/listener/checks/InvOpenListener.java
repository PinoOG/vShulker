package it.pino.vshulker.bukkit.listener.checks;

import it.pino.vshulker.api.listener.Listener;
import it.pino.vshulker.api.snapshot.Snapshot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

public final class InvOpenListener implements Listener<InventoryOpenEvent> {


    @Override
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(@NotNull final InventoryOpenEvent event) {
        if(event.isCancelled()) return;

        var inv = event.getInventory();

        if(inv.getHolder(false) instanceof Snapshot) return;

        if(inv.getViewers().size() > 1) event.setCancelled(true);
    }
}
