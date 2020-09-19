package me.marvin.menu.standalone;

import me.marvin.menu.listener.ButtonInteractionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This bootstrap provides an accessibility point towards this
 * API if the user does not want to include it in their project
 */
public class StandaloneMenuBootstrap extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ButtonInteractionListener(), this);
    }

    @Override
    public void onDisable() {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (!plugin.getDescription().getDepend().contains(this.getName())) continue;

            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }
}
