package it.pino.vshulker.api.listener;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface Listener<E extends Event> extends org.bukkit.event.Listener {

    default void register(@NotNull JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getLogger().info("Registered listeners for " + this.getClass().getSimpleName());
    }

    default void unregister(@NotNull JavaPlugin plugin){
        HandlerList.unregisterAll(this);
        plugin.getLogger().info("Unregistered listeners for " + this.getClass().getSimpleName());
    }

    /**
     * Requires the {@link org.bukkit.event.EventHandler} annotation to be present on the method.
     */
    void onEvent(@NotNull E event);
}
