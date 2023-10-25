package fr.crabii.homes.listeners;

import fr.crabii.homes.commands.CommandHomeGui;
import fr.crabii.homes.managers.Managers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GuiListener implements Listener {

    HashMap<UUID, Integer> homesPlayerIndex = new HashMap<>();
    static int maxPerPage = 9;
    int index = 0;
    List<String> homes;

    @EventHandler
    public void openInventory(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inv = e.getInventory();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "Homes")) {
            homes = (List<String>) Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist");

            if (homes.size() > maxPerPage) {

                homesPlayerIndex.put(player.getUniqueId(), 9);
                index = homesPlayerIndex.get(player.getUniqueId());
                for (int i = 0; i < index; i++){
                    ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.YELLOW + homes.get(i));
                    item.setItemMeta(meta);
                    inv.setItem(i, item);
                }
                ItemStack next = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                inv.setItem(17, next);

            } else {
                for (int i = 0; i < homes.size(); i++) {
                    ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.YELLOW + homes.get(i));
                    item.setItemMeta(meta);
                    inv.setItem(i, item);
                }
            }


        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        homes = (List<String>) Managers.getManagers().data.getConfig().getList("players." + player.getUniqueId() + ".homeslist");
        Inventory inv = e.getInventory();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "Homes")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS_PANE && homesPlayerIndex.get(player.getUniqueId()) < homes.size()) {
                    inv.clear();

                    int tempindex = homesPlayerIndex.get(player.getUniqueId());
                    homesPlayerIndex.put(player.getUniqueId(), tempindex+9);
                    Bukkit.getConsoleSender().sendMessage("index " + index);

                    for (int i = tempindex; i < homesPlayerIndex.get(player.getUniqueId()); i++){
                        Bukkit.getConsoleSender().sendMessage("i " + i);
                        if(i < homes.size()) {
                            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "CheckIfEnter");
                            ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(ChatColor.YELLOW + homes.get(i));
                            item.setItemMeta(meta);
                            inv.setItem(i-tempindex, item);
                        }
                    }
                    ItemStack next = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                    ItemStack back = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                    inv.setItem(17, next);
                    inv.setItem(9, back);

                }
            }
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.ORANGE_STAINED_GLASS_PANE && homesPlayerIndex.get(player.getUniqueId()) > 9) {
                    inv.clear();

                    int tempindex = homesPlayerIndex.get(player.getUniqueId())-18;
                    homesPlayerIndex.put(player.getUniqueId(), homesPlayerIndex.get(player.getUniqueId())-9);
                    Bukkit.getConsoleSender().sendMessage("index " + index);

                    for (int i = homesPlayerIndex.get(player.getUniqueId()); i >= tempindex; i--){
                        Bukkit.getConsoleSender().sendMessage("i " + i);
                        if(i < homes.size()) {
                            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "CheckIfEnter");
                            ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(ChatColor.YELLOW + homes.get(i));
                            item.setItemMeta(meta);
                            inv.setItem(i-tempindex, item);
                            Bukkit.getConsoleSender().sendMessage("Place " + String.valueOf(i-tempindex));
                        }
                    }
                    ItemStack next = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                    ItemStack back = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
                    inv.setItem(17, next);
                    inv.setItem(9, back);

                }

                if(e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getType() == Material.END_PORTAL_FRAME) {
                        String homeName = e.getCurrentItem().getItemMeta().getDisplayName();
                        player.performCommand("home " + homeName.replace("§e", ""));
                    }
                }
            }

        }



    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

    }

}
