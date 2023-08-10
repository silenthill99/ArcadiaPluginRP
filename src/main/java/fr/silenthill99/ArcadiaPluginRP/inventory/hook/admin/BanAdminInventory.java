package fr.silenthill99.ArcadiaPluginRP.inventory.hook.admin;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.BanAdminHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BanAdminInventory extends AbstractInventory<BanAdminHolder>
{
    public BanAdminInventory()
    {
        super(BanAdminHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        BanAdminHolder holder = new BanAdminHolder(target);
        Inventory inv = createInventory(holder, 9, ChatColor.DARK_RED + "Bannir " + target.getName());
        int slot = 1;
        for (Bannir bannir : Bannir.values())
        {
            holder.bannir.put(slot, bannir);
            inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(ChatColor.RED + bannir.getName()).toItemStack());
        }
        inv.setItem(8, RETOUR);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, BanAdminHolder holder) {
        OfflinePlayer target = holder.getTarget();
        Bannir bannir = holder.bannir.get(event.getSlot());
        switch (current.getType())
        {
            case PAPER:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "ipban " + target.getName() + " " + bannir.getName());
                break;
            }
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
                break;
            }
            default:
                break;
        }
    }

    public enum Bannir
    {
        CONTOURNEMENT("Contournement de sanctions"),
        USURPATION("Usurpation d'identité")
        ;

        private final String name;
        Bannir(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
