package net.zoizoi.plugin.commandboard;

import net.zoizoi.plugin.commandboard.System.PluginConfig;
import net.zoizoi.plugin.commandboard.System.PluginEventsListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public Main(){
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        new PluginConfig();
        getLogger().info("CommandBoard_Plugin Load...");

        getServer().getPluginManager().registerEvents(new PluginEventsListener(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("CommandBoard_Plugin Unload...");
    }
}
