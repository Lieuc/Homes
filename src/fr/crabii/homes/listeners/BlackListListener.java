package fr.crabii.homes.listeners;

import fr.crabii.homes.managers.Managers;
import fr.crabii.homes.tools.ConfigBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlackListListener implements Listener {

    String prefix = ConfigBuilder.getString("prefix");
    @EventHandler
    public void BlackListListener(PlayerCommandPreprocessEvent e){
        String command = e.getMessage();
        Player player = e.getPlayer();
        if(command.contains("home")) {
            if (Managers.getManagers().blacklist.contains(player.getWorld().getName())) {
                if (!player.hasPermission("homes.bypass")){
                    e.setCancelled(true);
                    player.sendMessage(prefix + "§cVous ne pouvez pas utiliser les homes ici !");
                }
            }
        }
    }
}
