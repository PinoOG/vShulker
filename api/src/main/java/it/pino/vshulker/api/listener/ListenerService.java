package it.pino.vshulker.api.listener;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ListenerService {

    @NotNull Collection<Listener<?>> getRegisterListeners();

    default void registerListeners(@NotNull final JavaPlugin plugin){
        getRegisterListeners().forEach(listener -> listener.register(plugin));
    }

    default void unregisterListeners(@NotNull final JavaPlugin plugin){
        getRegisterListeners().forEach(listener -> listener.unregister(plugin));
    }
}
