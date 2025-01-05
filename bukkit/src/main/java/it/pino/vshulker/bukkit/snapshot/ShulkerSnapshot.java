package it.pino.vshulker.bukkit.snapshot;

import it.pino.vshulker.api.snapshot.Snapshot;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.translation.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

final class ShulkerSnapshot extends Snapshot {

    @NotNull
    private final ItemStack[] contents;
    @NotNull
    private final Inventory shulker;
    @NotNull
    private final UUID identifier;

    ShulkerSnapshot(@NotNull final ItemStack[] contents, @NotNull final UUID identifier) {
        this.contents = contents;
        this.shulker = this.createSnapshot(contents);
        this.identifier = identifier;
    }

    @Override
    public @NotNull UUID getIdentifier() {
        return this.identifier;
    }

    @Override
    public @NotNull ItemStack[] getContents() {
        return this.contents;
    }

    @Override
    protected @NotNull Inventory createSnapshot(@NotNull final ItemStack[] contents) {
        var title = Component.translatable("block.minecraft.shulker_box");
        var inventory = Bukkit.createInventory(this, InventoryType.SHULKER_BOX, title);
        inventory.setContents(contents);
        return inventory;
    }


    @Override
    public @NotNull Inventory getInventory() {
        return this.shulker;
    }
}
