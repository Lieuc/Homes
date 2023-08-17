package fr.crabii.homes.commands;

import fr.crabii.homes.managers.Managers;
import fr.crabii.homes.tools.ConfigBuilder;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public class CommandSethome implements CommandExecutor {


    private List<String> homes;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String prefix = ConfigBuilder.getString("prefix");


        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Permet de récupérer les homes du joueur si il en a ou de créer une liste de home vide
            if (Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist") == null) {
                homes = new ArrayList<>();
            } else {
                homes = (List<String>) Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist");
            }

            // Vérification et création du home
            if (args.length < 1 || args.length > 1) {
                player.sendMessage("§c- /sethome <nom du home>");
                return false;
            } else {
                if(!player.hasPermission("homes.morehome") || !player.hasPermission("homes.unlimited")) {
                    if (homes.size() < ConfigBuilder.getInt("maxhomes")) {
                        if (homes.contains(args[0])) {
                            player.sendMessage(prefix + "§cCe home existe déja");
                            return false;
                        } else {
                            homes.add(args[0]);
                            setHome(player.getLocation(), args[0], player.getUniqueId());
                            player.sendMessage("§eVous avez créé le home §b" + args[0]);
                            return false;
                        }
                    } else {
                        player.sendMessage(prefix + "Vous ne pouvez plus faire de home !");
                        return false;


                    }
                } else {
                    if (player.hasPermission("homes.unlimited")) {
                        homes.add(args[0]);
                        setHome(player.getLocation(), args[0], player.getUniqueId());
                        player.sendMessage("§eVous avez créé le home §b" + args[0]);
                        return false;
                    } else if(homes.size() < ConfigBuilder.getInt("morehome")) {
                        homes.add(args[0]);
                        setHome(player.getLocation(), args[0], player.getUniqueId());
                        player.sendMessage("§eVous avez créé le home §b" + args[0]);
                        return false;
                    } else {
                        player.sendMessage(prefix + "Vous ne pouvez plus faire de home !");
                        return false;
                    }
                }


            }
        } else {
            sender.sendMessage(prefix + "§cVous devez être un joueur pour executer cette commande");
            return false;
        }

    }

    // Procédure permettant de créer un home
    private void setHome(Location location, String name, UUID uuid) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        float yaw = location.getYaw();
        float pitch = location.getPitch();

        String worldName = location.getWorld().getName();

        Managers.getManagers().data.getConfig().set("players." + uuid + "." + name + ".x", x);
        Managers.getManagers().data.getConfig().set("players." + uuid + "." + name + ".y", y);
        Managers.getManagers().data.getConfig().set("players." + uuid + "." + name + ".z", z);

        Managers.getManagers().data.getConfig().set("players." + uuid + "." + name + ".yaw", yaw);
        Managers.getManagers().data.getConfig().set("players." + uuid + "." + name + ".pitch", pitch);

        Managers.getManagers().data.getConfig().set("players." + uuid + "." + name + ".worldname", worldName);
        Managers.getManagers().data.getConfig().set("players." + uuid + ".homeslist", homes);
        Managers.getManagers().data.saveConfig();

    }


}
