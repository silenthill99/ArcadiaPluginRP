package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.Main;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.OptionHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OptionInventory extends AbstractInventory<OptionHolder>
{
    Main main = Main.getInstance();
    public OptionInventory()
    {
        super(OptionHolder.class);
    }

    ItemStack whitelist_off = new ItemBuilder(Material.GREEN_DYE).setName(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Activer la whitelist").setLore("La whitelist est désactivée !").toItemStack();
    ItemStack whitelist_on = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Désactiver la whitelist").setLore("La whitelist est désactivée !").toItemStack();

    @Override
    public void openInventory(Player player, Object... args)
    {
        OptionHolder holder = new OptionHolder();

        ItemStack stop = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "Arrêter le serveur").setLore("Uniquement en cas d'urgence !").toItemStack();

        Inventory inv = createInventory(holder, 27, "Options");
        if (Bukkit.getServer().hasWhitelist())
            inv.setItem(0, whitelist_on);
        else
            inv.setItem(0, whitelist_off);
        inv.setItem(1, stop);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, OptionHolder holder)
    {
        switch (current.getType())
        {
            case RED_DYE:
            {
                Bukkit.getServer().setWhitelist(false);
                player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §aVous avez désactivé la vhitelist");
                event.getClickedInventory().setItem(0, whitelist_off);
                break;
            }
            case GREEN_DYE:
            {
                Bukkit.getServer().setWhitelist(true);
                player.sendMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " Vous avez activé la whitelist");
                event.getClickedInventory().setItem(0, whitelist_on);
                break;
            }
            case RED_WOOL:
            {
                player.closeInventory();
                Bukkit.broadcastMessage(Main.getInstance().getConfig().getString("options.prefix").replace("&", "§") + " §aArrêt imminent du serveur dans 30 secondes !");
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                    if (!Bukkit.getServer().hasWhitelist())
                        Bukkit.getServer().setWhitelist(true);
                }, 600);
            }
            default:
                break;
        }
    }
}
