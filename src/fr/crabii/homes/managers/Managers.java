package fr.crabii.homes.managers;

import fr.crabii.homes.Main;
import fr.crabii.homes.commands.EventsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Managers {

    private static Main instance;
    private static Managers managers;
    public List<String> playersuuid;
    public DataManager data;
    private Collection<Player> players = (List<Player>) Bukkit.getOnlinePlayers();


    public void load(Main instance) {
        Managers.instance = instance;
        Managers.managers = this;
        Bukkit.getConsoleSender().sendMessage("§6[§eHomes§6] §aLoading");

        instance.saveDefaultConfig();
        data = new DataManager(instance);

        CommandsManager.register(instance);
        EventsManager.register(instance);

        playersuuid = (List<String>) data.getConfig().getList("uuidplayers");
        Bukkit.getConsoleSender().sendMessage(players.toString());
        registerNewPlayer(players);

    }

    public static Main getInstance() {
        return instance;
    }

    public static Managers getManagers() {
        return managers;
    }
    public void registerNewPlayer(Collection<Player> a) {
        for (Player pls : players) {
            if (!playersuuid.contains(String.valueOf(pls.getUniqueId()))) {
                Managers.getManagers().playersuuid.add(String.valueOf(pls.getUniqueId()));

                String u = String.valueOf(pls.getUniqueId());
                Managers.getManagers().data.getConfig().set("players." + u, "");
                Managers.getManagers().data.getConfig().set("players." + u + ".homeslist", "");
                Managers.getManagers().data.saveConfig();
            }

        }
        Managers.getManagers().data.getConfig().set("uuidplayers", playersuuid);
    }

    public boolean checkHome(String name, Player player){
        boolean value;
        if (data.getConfig().getList("players." + player.getUniqueId() + ".homeslist").contains(name)) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

}



