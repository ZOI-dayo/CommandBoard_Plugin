package net.zoizoi.plugin.commandboard.System;

import net.zoizoi.plugin.commandboard.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class PluginConfig {
  public static FileConfiguration config;
  // private static JavaPlugin plugin;
  public PluginConfig() {
    // PluginConfig.plugin = plugin;
    // config.ymlが存在しない場合はファイルに出力します。
    Main.plugin.saveDefaultConfig();
    // config.ymlを読み込みます。
    config = Main.plugin.getConfig();
  }
  public static void ReloadConfig(){
    // config.ymlが存在しない場合はファイルに出力します。
    Main.plugin.saveDefaultConfig();
    // config.ymlを読み込みます。
    config = Main.plugin.getConfig();
  }
}
