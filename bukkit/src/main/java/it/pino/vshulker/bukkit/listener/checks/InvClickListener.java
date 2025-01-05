package it.pino.vshulker.bukkit.listener.checks;

import it.pino.vshulker.api.listener.Listener;
import it.pino.vshulker.api.snapshot.Snapshot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public final class InvClickListener implements Listener<InventoryClickEvent> {


    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(@NotNull final InventoryClickEvent event) {
        if(!(event.getInventory().getHolder(false) instanceof Snapshot)) return;

        if(event.getCurrentItem() == null) return;

        var type = event.getCurrentItem().getType();
        if(!type.name().contains("SHULKER_BOX")) return;

        event.setCancelled(true);
    }
}
