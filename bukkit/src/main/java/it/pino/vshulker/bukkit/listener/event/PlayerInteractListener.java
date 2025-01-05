package it.pino.vshulker.bukkit.listener.event;

import it.pino.vshulker.api.listener.Listener;
import it.pino.vshulker.api.snapshot.SnapshotService;
import org.bukkit.Bukkit;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class PlayerInteractListener implements Listener<PlayerInteractEvent> {

    @NotNull
    private final SnapshotService snapshotService;
    @NotNull
    private final JavaPlugin plugin;

    public PlayerInteractListener(@NotNull final SnapshotService snapshotService, @NotNull final JavaPlugin plugin) {
        this.snapshotService = snapshotService;
        this.plugin = plugin;
    }


    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(@NotNull final PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        var item = event.getItem();
        if (item == null || !item.getType().name().contains("SHULKER_BOX")) return;

        if (!(item.getItemMeta() instanceof BlockStateMeta blockStateMeta)) return;
        if (!(blockStateMeta.getBlockState() instanceof ShulkerBox shulker)) return;
        event.setCancelled(true);

        
        var key = this.snapshotService.getKey();
        var identifier = UUID.randomUUID();

        var meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, identifier.toString());
        item.setItemMeta(meta);

        var uniqueId = event.getPlayer().getUniqueId();
        this.snapshotService.addSnapshot(uniqueId, shulker, identifier);

        event.getPlayer().closeInventory(InventoryCloseEvent.Reason.PLUGIN);

        this.snapshotService.getSnapshot(uniqueId).ifPresent(snapshot -> {
            shulker.getInventory().clear();
            Bukkit.getScheduler().runTask(this.plugin, () -> event.getPlayer().openInventory(snapshot.getInventory()));
        });

    }
}
