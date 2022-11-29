package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.AdminOptionHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdminOptionInventory extends AbstractInventory<AdminOptionHolder>
{
    public AdminOptionInventory()
    {
        super(AdminOptionHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        AdminOptionHolder holder = new AdminOptionHolder(target);

        ItemStack Casier = new ItemBuilder(Material.BOOK).setName("Casier Administratif").toItemStack();
        ItemStack Clear = new ItemBuilder(Material.BLAZE_POWDER).setName(ChatColor.YELLOW + "Clear l'inventaire").toItemStack();
        ItemStack Infos = new ItemBuilder(Material.ENDER_PEARL).setName(ChatColor.YELLOW + "Infos joueur").toItemStack();

        Inventory inv = createInventory(holder, 9, ChatColor.DARK_RED + "Option Admin | " + target.getName());
        inv.setItem(2, Casier);
        inv.setItem(3, Clear);
        inv.setItem(4, Infos);
        inv.setItem(8, CLOSE);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, AdminOptionHolder holder) {
        OfflinePlayer target = holder.getTarget();
    }
}
