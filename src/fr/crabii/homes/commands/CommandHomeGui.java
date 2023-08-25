package fr.crabii.homes.commands;

import fr.crabii.homes.managers.Managers;
import fr.crabii.homes.tools.ConfigBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CommandHomeGui implements CommandExecutor {

    static int maxPerPage = 9;
    int homeCount;
    int pages;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Seul un joueur peut executer cette commande !");
            return true;
        }

        Player player = (Player) sender;



        Inventory inv = Bukkit.createInventory(player, 9*2, ChatColor.YELLOW + "Homes");
        player.openInventory(inv);


        return false;
    }



}
