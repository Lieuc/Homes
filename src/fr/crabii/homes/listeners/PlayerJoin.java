package fr.crabii.homes.listeners;

import fr.crabii.homes.managers.Managers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;


public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Managers.getManagers().registerNewPlayer((List<Player>) Bukkit.getOnlinePlayers());
    }
}
