package fr.crabii.homes.commands;

import fr.crabii.homes.managers.Managers;
import fr.crabii.homes.tools.ConfigBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGohome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1 || args.length > 2) {
                player.sendMessage(ChatColor.RED + "syntaxe: [<player>] [<home>]");
                return false;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (!Managers.getManagers().data.getConfig().getList("players." + target.getUniqueId() + ".homeslist").toString().contains(args[1])) {
                    player.sendMessage(ChatColor.RED + "Erreur:" + ChatColor.YELLOW + "Ce home n'existe pas");
                    return false;
                } else {
                    otherhomeTp(args[1], player);
                    player.sendMessage(ConfigBuilder.getString("prefix") + ChatColor.YELLOW + "Téléportation au home " + ChatColor.BLUE + args[1]);
                }
            }
        }
        return false;
    }


    public void otherhomeTp(String name, Player player) {
        double x = Managers.getManagers().data.getConfig().getDouble("players." + player.getUniqueId() + "." + name + ".x");
        double y = Managers.getManagers().data.getConfig().getDouble("players." + player.getUniqueId() + "." + name + ".y");
        double z = Managers.getManagers().data.getConfig().getDouble("players." + player.getUniqueId() + "." + name + ".z");

        float yaw = Managers.getManagers().data.getConfig().getLong("players." + player.getUniqueId() + "." + name + ".yaw");
        float pitch = Managers.getManagers().data.getConfig().getLong("players." + player.getUniqueId() + "." + name + ".pitch");

        String worldName = Managers.getManagers().data.getConfig().getString("players." + player.getUniqueId() + "." + name + ".worldname");

        Location tp = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
        player.teleport(tp);
    }



}
