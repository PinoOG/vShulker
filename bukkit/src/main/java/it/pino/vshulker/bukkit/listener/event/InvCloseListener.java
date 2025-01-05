package it.pino.vshulker.bukkit.listener.event;

import it.pino.vshulker.api.listener.Listener;
import it.pino.vshulker.api.snapshot.Snapshot;
import it.pino.vshulker.api.snapshot.SnapshotService;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;


public final class InvCloseListener implements Listener<InventoryCloseEvent> {

    @NotNull
    private final SnapshotService snapshotService;

    public InvCloseListener(@NotNull final SnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(@NotNull final InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder(false) instanceof Snapshot snapshot)) return;

        var identifier = snapshot.getIdentifier();
        var key = this.snapshotService.getKey();

        Arrays.stream(event.getPlayer().getInventory().getContents())
                .filter(Objects::nonNull)
                .filter(item -> item.getType().name().contains("SHULKER_BOX"))
                .forEach(item -> {
                    boolean hasKey = item.getItemMeta().getPersistentDataContainer().has(key);
                    if (!hasKey) return;

                    var value = item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
                    if (!value.equals(identifier.toString())) return;

                    final BlockStateMeta bsm = (BlockStateMeta) item.getItemMeta();
                    final ShulkerBox shulker = (ShulkerBox) bsm.getBlockState();

                    shulker.getInventory().clear();
                    shulker.getInventory().setContents(snapshot.getInventory().getContents().clone());

                    bsm.setBlockState(shulker);
                    item.setItemMeta(bsm);

                    var meta = item.getItemMeta();
                    meta.getPersistentDataContainer().remove(key);
                    item.setItemMeta(meta);
                });

    }
}
