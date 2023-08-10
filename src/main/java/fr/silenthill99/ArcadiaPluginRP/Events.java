package fr.silenthill99.ArcadiaPluginRP;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class Events implements Listener {

    LuckPerms api = LuckPermsProvider.get();
    ItemBuilder Argent = new ItemBuilder(Material.GOLD_INGOT).setName("100 €");

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block current = event.getClickedBlock();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            switch (current.getType()){
                case CAULDRON:
                    Inventory poubelle = Bukkit.createInventory(null, 9, "Poubelle");
                    player.openInventory(poubelle);
                    break;
                /**
                case DROPPER:
                    event.setCancelled(true);

                    ItemBuilder deposer = new ItemBuilder(Material.GOLD_INGOT).setName("Déposer de l'argent");
                    ItemBuilder retirer = new ItemBuilder(Material.GOLD_INGOT).setName("Retirer de l'argent");

                    Inventory atm = Bukkit.createInventory(null, 27, "Distributeur");
                    atm.setItem(10, deposer.toItemStack());
                    atm.setItem(16, retirer.toItemStack());
                    player.openInventory(atm);
                 */
                default:
                    break;
            }
        } else if (event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if (!player.getItemInHand().equals(Material.BRICK)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        User user = api.getUserManager().getUser(player.getName());
        try {
            event.setJoinMessage("§2[+] " + user.getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName());
        } catch (IllegalArgumentException exception) {
            event.setJoinMessage("§2[+] " + player.getName());
        }
        if (!player.hasPlayedBefore()) {
            Bukkit.broadcastMessage("\n"+Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + "  " + player.getName() + " §bVient de débarquer à AnamaraCity. Veuillez lui souhaiter la bienvenue !\n");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco set " + player.getName() + " 3000");
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = api.getUserManager().getUser(player.getName());
        try {
            event.setQuitMessage("§4[-] " + user.getCachedData().getMetaData().getPrefix().replace("&", "§") + player.getName());
        } catch (IllegalArgumentException exception) {
            event.setQuitMessage("§4[-] " + player.getName());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        Inventory inv = event.getClickedInventory();

        /**
        if (event.getView().getTitle().startsWith("Distributeur")) {
            event.setCancelled(true);
            switch (current.getItemMeta().getDisplayName()) {
                case "Déposer de l'argent":
                    if (player.getInventory().contains(Argent.toItemStack())) {
                        player.getInventory().removeItem(Argent.toItemStack());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + " 100");
                    } else {
                        player.sendMessage("§4Vous n'avez pas d'argent sur vous !");
                    }
                    break;
                case "Retirer de l'argent":
                    if (Main.getInstance().economy.has(player, 100)) {
                        player.getInventory().addItem(Argent.toItemStack());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + player.getName() + " 100");
                    } else {
                        player.sendMessage("§4Tu n'as pas assez d'argent sur ton compte !");
                    }
                    break;
            }
        }
         */
    }
}