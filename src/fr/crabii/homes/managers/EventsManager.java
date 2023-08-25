package fr.crabii.homes.managers;

import fr.crabii.homes.Main;
import fr.crabii.homes.listeners.GuiListener;
import fr.crabii.homes.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventsManager {
    public static void register(Main instance) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerJoin(), instance);
        pm.registerEvents(new GuiListener(), instance);
    }
}
