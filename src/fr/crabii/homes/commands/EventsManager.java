package fr.crabii.homes.commands;

import fr.crabii.homes.Main;
import fr.crabii.homes.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventsManager {
    public static void register(Main instance) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerJoin(), instance);
    }
}
