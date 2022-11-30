package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.BanHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BanInventory extends AbstractInventory<BanHolder>
{
    public BanInventory()
    {
        super(BanHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        BanHolder holder = new BanHolder(target);
        Inventory inv = createInventory(holder, 54, ChatColor.DARK_RED + "Bannir " + target.getName());
        int slot = 1;
        for (BanTemp ban_temp : BanTemp.values())
        {
            if (slot != 8)
            {
                holder.ban_temp.put(slot, ban_temp);
                inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(ChatColor.GOLD + ban_temp.getName()).setLore(ban_temp.getLore()).toItemStack());
            }
            else
            {
                slot++;
            }
        }
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, BanHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        BanTemp ban_temp = holder.ban_temp.get(event.getSlot());
        Ban ban = holder.ban.get(event.getSlot());
    }

    public enum BanTemp
    {
        INSULTES_STAFF("Insultes staff", "3d", "Durée : 3 jours"),
        FK_INVO("Freekill involontaire", "24h", "Durée : 24 heures"),
        FREEKILL("Freekill volontaire", "15d", "Durée : 15 jours"),
        FK_AVEU("Freekill - Aveu", "10d", "Durée : 10 jours"),
        FK_STUFF_RENDU("Freekill - stuff rendu", "7d", "Durée : 1 semaine"),
        DECO_INTER("Déco inter", "24h", "Durée : 24 heures"),
        MENSONGE_STAFF("Mensonge Staff", "2d", "Durée : 2 jours"),
        REFUS_DINTER("Refus d'inter", "3d", "Durée : 3 jours")
        ;
        private final String name;
        private final String duration;
        private final String[] lore;
        BanTemp(String name, String duration, String... lore)
        {
            this.name = name;
            this.duration = duration;
            this.lore = lore;
        }
        public String getName()
        {
            return this.name;
        }

        public String getDuration()
        {
            return this.duration;
        }

        public String[] getLore()
        {
            return this.lore;
        }
    }

    public enum Ban
    {
        ;
    }
}
