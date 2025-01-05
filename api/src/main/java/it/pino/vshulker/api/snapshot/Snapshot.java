package it.pino.vshulker.api.snapshot;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class Snapshot implements InventoryHolder {

    @NotNull
    public abstract UUID getIdentifier();

    @NotNull
    public abstract ItemStack[] getContents();

    @NotNull
    protected abstract Inventory createSnapshot(@NotNull final ItemStack[] contents);

}
