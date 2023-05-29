package fr.crabii.homes.commands;

import fr.crabii.homes.managers.Managers;
import fr.crabii.homes.tools.ConfigBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class CommandHome implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // Vérification et execution de la fonction pour ce téléporter à un home
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                if (Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist").size() == 0) {
                    player.sendMessage("§eListe des homes: §cAuccun");
                    return false;
                } else {
                    player.sendMessage("§eListe de homes: §b" + Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist").toString().replace("[", "").replace("]", ""));
                    return false;
                }
            } else if (args.length > 1) {
                player.sendMessage("§c- ?/home <nom du home>");
                return false;
            } else {
                if (Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist").contains(args[0])) {
                    homeTp(args[0], player);
                    player.sendMessage(ConfigBuilder.getString("prefix") + "§eTéléportation au home: §b" + args[0]);
                    return false;
                } else {
                    player.sendMessage(ConfigBuilder.getString("prefix") + "§cCe home n'existe pas");
                    return false;
                }
            }
        }
        return false;


    }
    // Procedure de téléportation à un home
    public void homeTp(String name, Player player) {
        double x = Managers.getManagers().data.getConfig().getDouble("players." + player.getUniqueId() + "." + name + ".x");
        double y = Managers.getManagers().data.getConfig().getDouble("players." + player.getUniqueId() + "." + name + ".y");
        double z = Managers.getManagers().data.getConfig().getDouble("players." + player.getUniqueId() + "." + name + ".z");

        float yaw = Managers.getManagers().data.getConfig().getLong("players." + player.getUniqueId() + "." + name + ".yaw");
        float pitch = Managers.getManagers().data.getConfig().getLong("players." + player.getUniqueId() + "." + name + ".pitch");

        String worldName = Managers.getManagers().data.getConfig().getString("players." + player.getUniqueId() + "." + name + ".worldname");

        Location tp = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
        player.teleport(tp);
    }

    List<String> arguments = new ArrayList();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        arguments = (List<String>) Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist");

        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            for (String str : arguments) {
                if (str.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(str);
                }
            }
            return result;
        }
        return null;
    }
}



