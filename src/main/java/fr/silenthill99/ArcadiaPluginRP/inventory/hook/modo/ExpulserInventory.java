package fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.ExpulserHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExpulserInventory extends AbstractInventory<ExpulserHolder>
{
    public ExpulserInventory()
    {
        super(ExpulserHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        ExpulserHolder holder = new ExpulserHolder(target);
        Inventory inv = createInventory(holder, 9, "§3Expulser " + target.getName());
        int slot = 0;
        for (Expulser expulser : Expulser.values())
        {
            holder.expulser.put(slot, expulser);
            inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(ChatColor.AQUA + expulser.getName()).toItemStack());
        }
        inv.setItem(8, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ExpulserHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        Expulser expulser = holder.expulser.get(event.getSlot());
    }

    public enum Expulser
    {
        SPAM("Spam"),
        FLOOD("Flood"),
        SPAM_MICRO("Spam micro"),
        JOUEUR_AFK("Joueur AFK")
        ;
        private final String name;
        Expulser(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
