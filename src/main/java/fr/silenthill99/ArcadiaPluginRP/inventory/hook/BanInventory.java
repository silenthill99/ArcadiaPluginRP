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
        for (BanTemp ban_temp : BanTemp.values())
        {
            holder.ban_temp.put(ban_temp.getSlot(), ban_temp);
            inv.setItem(ban_temp.getSlot(), new ItemBuilder(Material.PAPER).setName(ChatColor.GOLD + ban_temp.getName()).setLore(ban_temp.getLore()).toItemStack());
        }
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, BanHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        BanTemp ban_temp = holder.ban_temp.get(event.getSlot());
        Ban ban = holder.ban.get(event.getSlot());

        int i = 0;
    }

    public enum BanTemp
    {
        INSULTES_STAFF(1, "Insultes staff", "3d", "Durée : 3 jours"),
        FK_INVO(2, "Freekill involontaire", "24h", "Durée : 24 heures"),
        FREEKILL(3, "Freekill volontaire", "15d", "Durée : 15 jours"),
        FK_AVEU(4, "Freekill - Aveu", "10d", "Durée : 10 jours"),
        FK_STUFF_RENDU(5, "Freekill - stuff rendu", "7d", "Durée : 1 semaine"),
        DECO_INTER(6, "Déco inter", "24h", "Durée : 24 heures"),
        MENSONGE_STAFF(7, "Mensonge Staff", "2d", "Durée : 2 jours"),
        REFUS_DINTER(9,"Refus d'inter", "3d", "Durée : 3 jours"),
        FAUX_TICKET_MASSIF(10, "Faux ticket massif", "5d", "Durée : 5 jours"),
        SPAM_MASSIF(11, "Spam massif", "10m", "Durée : 10 minutes"),
        INTRUSION_BATIMENT_STAFF(12, "Intrusion bâtiment staff", "1h", "Durée : 1h"),
        CHEAT(13, "Cheat", "3mo", "Durée : 3 mois"),
        USEBUG(14, "UseBug", "3d", "Durée : 3 jours")
        ;
        private final int slot;
        private final String name;
        private final String duration;
        private final String[] lore;
        BanTemp(int slot, String name, String duration, String... lore)
        {
            this.slot = slot;
            this.name = name;
            this.duration = duration;
            this.lore = lore;
        }

        public int getSlot()
        {
            return this.slot;
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
