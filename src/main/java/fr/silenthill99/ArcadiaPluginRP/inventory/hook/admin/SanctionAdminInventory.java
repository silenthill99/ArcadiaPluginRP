package fr.silenthill99.ArcadiaPluginRP.inventory.hook.admin;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SanctionAdminInventory extends AbstractInventory<SanctionAdminHolder>
{
    public SanctionAdminInventory() {
        super(SanctionAdminHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        SanctionAdminHolder holder = new SanctionAdminHolder(target);

        ItemStack Bannir = new ItemBuilder(Material.BOOK).setName(ChatColor.RED + "Bannir le joueur").toItemStack();

        Inventory inv = createInventory(holder, 9, ChatColor.LIGHT_PURPLE + "Sanctionner " + target.getName());
        inv.setItem(5, Bannir);
        inv.setItem(8, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, SanctionAdminHolder holder) {
        OfflinePlayer target = holder.getTarget();
        switch (current.getType())
        {
            case BOOK:
            {
                InventoryManager.openInventory(player, InventoryType.BAN_ADMIN, target);
            }
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
            }
        }
    }
}
