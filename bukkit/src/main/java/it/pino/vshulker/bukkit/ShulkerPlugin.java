package it.pino.vshulker.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

public final class ShulkerPlugin extends JavaPlugin {

    @Nullable
    private ShulkerBukkit shulkerBukkit;

    @Override
    public void onEnable(){
        try {
            getLogger().info("########## vShulker ##########");
            shulkerBukkit = new ShulkerBukkit(this);
            shulkerBukkit.load();
            getLogger().info("########## vShulker ##########");
        }catch (Exception e){
            this.getLogger().log(Level.SEVERE, e, () -> "An error occurred while enabling Shulker");
            this.getServer().shutdown();
        }
    }

    @Override
    public void onDisable(){
        if (shulkerBukkit == null) return;
        shulkerBukkit.unload();
    }
}
