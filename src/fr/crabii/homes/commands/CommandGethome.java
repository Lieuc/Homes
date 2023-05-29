package fr.crabii.homes.commands;

import fr.crabii.homes.managers.Managers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGethome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 1 || args.length > 1){
            sender.sendMessage(ChatColor.RED + "Syntaxe: /gethome [<player>]");
            return false;
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            sender.sendMessage("§eHomes de " + target.getName() + ": §b" + Managers.getManagers().data.getConfig().getList("players." + target.getUniqueId() + ".homeslist").toString().replace("[", "").replace("]", ""));
        }
        return false;
    }
}
