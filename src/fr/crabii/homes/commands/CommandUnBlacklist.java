package fr.crabii.homes.commands;

import fr.crabii.homes.managers.Managers;
import fr.crabii.homes.tools.ConfigBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUnBlacklist implements CommandExecutor {

    String prefix = ConfigBuilder.getString("prefix");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "§cVous devez être un joueur pour executer cette commande !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(prefix + "§cVous devez entrer le nom d'un monde !");
            return false;
        }

        if (!Managers.getManagers().blacklist.contains(args[0])) {
            player.sendMessage(prefix + "§cCe monde n'est pas dans la blacklist");
            return false;
        }

        Managers.getManagers().blacklist.remove(args[0]);
        ConfigBuilder.set("blacklist", Managers.getManagers().blacklist);
        player.sendMessage(prefix + "§eVous avez retiré le monde §b" + args[0] + " §ede la blacklist");

        return false;
    }
}
