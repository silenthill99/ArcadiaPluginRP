package fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.modo.BanHolder;
import org.bukkit.Bukkit;
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

        ItemStack Bannissements_temporaires = new ItemBuilder(Material.ORANGE_DYE).setName(ChatColor.GOLD + "Bannissement temporaire").toItemStack();
        ItemStack Bannissements_permanents = new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED + "Bannissement permanent").toItemStack();

        Inventory inv = createInventory(holder, 54, ChatColor.DARK_RED + "Bannir " + target.getName());
        for (BanTemp ban_temp : BanTemp.values())
        {
            holder.ban_temp.put(ban_temp.getSlot(), ban_temp);
            inv.setItem(ban_temp.getSlot(), new ItemBuilder(Material.PAPER).setName(ChatColor.GOLD + ban_temp.getName()).setLore(ban_temp.getLore()).toItemStack());
        }
        int slot = 27;
        for (Ban ban : Ban.values())
        {
            holder.ban.put(slot, ban);
            inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(ChatColor.RED + ban.getName()).toItemStack());
        }
        inv.setItem(35, RETOUR);
        inv.setItem(44, Bannissements_temporaires);
        inv.setItem(53, Bannissements_permanents);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, BanHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        BanTemp ban_temp = holder.ban_temp.get(event.getSlot());
        Ban ban = holder.ban.get(event.getSlot());
        switch (current.getType())
        {
            case PAPER:
            {
                if (holder.ban_temp.containsValue(ban_temp))
                {
                    Bukkit.dispatchCommand(player, "tempipban " + target.getName() + " " + ban_temp.getDuration() + " " + ban_temp.getName());
                }
                else
                {
                    Bukkit.dispatchCommand(player, "ipban " + target.getName() + " " + ban.getName());
                }
                break;
            }
            default:
            {
                break;
            }
        }
    }

    public enum BanTemp
    {
        INSULTES_STAFF(1, "Insultes staff", "3d", "Dur??e : 3 jours"),
        FK_INVO(2, "Freekill involontaire", "24h", "Dur??e : 24 heures"),
        FREEKILL(3, "Freekill volontaire", "15d", "Dur??e : 15 jours"),
        FK_AVEU(4, "Freekill - Aveu", "10d", "Dur??e : 10 jours"),
        FK_STUFF_RENDU(5, "Freekill - stuff rendu", "7d", "Dur??e : 1 semaine"),
        DECO_INTER(6, "D??co inter", "24h", "Dur??e : 24 heures"),
        MENSONGE_STAFF(7, "Mensonge Staff", "2d", "Dur??e : 2 jours"),
        REFUS_DINTER(9,"Refus d'inter", "3d", "Dur??e : 3 jours"),
        FAUX_TICKET_MASSIF(10, "Faux ticket massif", "5d", "Dur??e : 5 jours"),
        SPAM_MASSIF(11, "Spam massif", "10m", "Dur??e : 10 minutes"),
        INTRUSION_BATIMENT_STAFF(12, "Intrusion b??timent staff", "1h", "Dur??e : 1h"),
        CHEAT(13, "Cheat", "3mo", "Dur??e : 3 mois"),
        USEBUG(14, "UseBug", "3d", "Dur??e : 3 jours"),
        ANTI_AFK(15, "Anti-AFK", "2d", "Dur??e : 2 jours"),
        TENTATIVE_FK_MODO(16, "Tentative FK modo", "2d", "Dur??e : 2 jours"),
        NORP_RECIDIVE_600_MIN(45, "NoRP [R??cidive | 600 min]", "10h"),
        NORP_RECIDIVE_120_MIN(46, "NoRP [R??cidive | 120 min]", "2h"),
        NORP_RECIDIVE_60_MIN(47, "NoRP [R??cidive | 60 min]", "1h"),
        PROPOS_OBSCENES(48, "Propos obsc??nes (Grave)", "8h", "Dur??e : 8 heures"),
        ACTES_OBSCENES(49, "Actes obsc??nes (Grave)", "8h", "Dur??e : 8 heures")
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
        NORP("NoRP en masse"),
        FK_MASSE("Freekill en masse"),
        DDOS("Menace DDoS"),
        TROLL("Troll"),
        DUPLICATION("Suspicion de duplication"),
        COUP_DETAT("Coup d'??tat serveur"),
        DENIGREMENT_DE_SERVEUR("D??nigrement de serveur"),
        INSULTE_SERVEUR("Insulte serveur")
        ;
        private final String name;

        Ban(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
