package it.pino.vshulker.bukkit.listener.event;

import it.pino.vshulker.api.listener.Listener;
import it.pino.vshulker.api.snapshot.Snapshot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;

public final class PlayerDamageListener implements Listener<EntityDamageEvent> {


    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(@NotNull EntityDamageEvent event) {
        if(event.isCancelled()) return;

        if(!(event.getEntity() instanceof Player player)) return;

        if(!(player.getOpenInventory().getTopInventory().getHolder(false) instanceof Snapshot)) return;

        player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }
}
