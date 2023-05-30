package fr.crabii.homes.commands;

import fr.crabii.homes.managers.Managers;
import fr.crabii.homes.tools.ConfigBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandDelhome implements CommandExecutor, TabCompleter {

    private List<String> homes;
    String prefix = ConfigBuilder.getString("prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Permet de récupérer les homes du joueur si il en a ou de créer une liste de home vide
            if (Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist") == null) {
                homes = new ArrayList<>();
            } else {
                homes = (List<String>) Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist");
            }

            // Vérification et suppression du home
            if (args.length < 1 || args.length > 1) {
                player.sendMessage(ConfigBuilder.getString("prefix") + "§c- /delhome <nom du home>");
                return false;
            } else {
                if (Managers.getManagers().checkHome(args[0], player) == true) {
                    homes.remove(args[0]);
                    Managers.getManagers().data.getConfig().set("players." + player.getUniqueId() + "." + args[0], null);
                    Managers.getManagers().data.getConfig().set("players." + player.getUniqueId() + ".homeslist" , homes);
                    Managers.getManagers().data.saveConfig();
                    player.sendMessage(prefix + "§eVous avez suprimé le home §b" + args[0]);
                    return false;
                } else {
                    player.sendMessage(prefix + "§cCe home n'existe pas");
                    return false;
                }
            }
        } else {
            sender.sendMessage(prefix + "§cVous decvez etre un joueur pour executer cette commande !");
            return false;
        }

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
