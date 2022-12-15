package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.Main;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.MacDoHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MacDoInventory extends AbstractInventory<MacDoHolder>
{
    Main main = Main.getInstance();
    public MacDoInventory()
    {
        super(MacDoHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        MacDoHolder holder = new MacDoHolder();

        ItemStack steak = new ItemBuilder(Material.COOKED_BEEF).setLore("Prix : 15€").toItemStack();
        ItemStack tarte = new ItemBuilder(Material.PUMPKIN_PIE).setLore("Prix : 15€").toItemStack();

        Inventory inv = createInventory(holder, 9, "Acheter à manger");
        inv.setItem(0, steak);
        inv.setItem(1, tarte);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, MacDoHolder holder) {
        switch (current.getType())
        {
            case COOKED_BEEF:
            {
                if (main.economy.has(player, 15))
                {
                    player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + player.getName() + " 15");
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Vous n'avez pas assez d'argent !");
                }
                break;
            }
            case PUMPKIN_PIE:
            {
                if (main.economy.has(player, 15))
                {
                    player.getInventory().addItem(new ItemStack(Material.PUMPKIN_PIE));
                    main.economy.withdrawPlayer(player, 15);
                }
                else
                {
                    player.sendMessage(ChatColor.RED + "Vous n'avez pas assez d'argent !");
                }
            }
        }
    }

    @Override
    public void onInteractEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity target = event.getRightClicked();
        if (target.getName().equalsIgnoreCase("MacDo")) InventoryManager.openInventory(player, InventoryType.MACDO);
    }
}
