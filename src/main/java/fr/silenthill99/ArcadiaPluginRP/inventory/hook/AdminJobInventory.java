package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.AdminJobHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AdminJobInventory extends AbstractInventory<AdminJobHolder>
{
    public AdminJobInventory() {
        super(AdminJobHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        AdminJobHolder holder = new AdminJobHolder(target);
        Inventory inv = createInventory(holder, 18, "Set team " + target.getName());
        for (Metiers metiers : Metiers.values())
        {
            holder.metiers.put(metiers.getSlot(), metiers);
            inv.setItem(metiers.getSlot(), new ItemBuilder(Material.PAPER).setName(metiers.getDisplayName()).toItemStack());
        }
        inv.setItem(17, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, AdminJobHolder holder) {
        OfflinePlayer target = holder.getTarget();
        Metiers metiers = holder.metiers.get(event.getSlot());
    }

    public enum Metiers
    {
        CITOYEN(1, ChatColor.DARK_GRAY + "Citoyen"),
        POMPIER(2, ChatColor.RED + "Sapeur-pompier"),
        MILITAIRE(3, ChatColor.GREEN + "Mili" + ChatColor.DARK_GREEN + "taire"),
        SECOURISTE(4, ChatColor.DARK_RED + "Secouriste"),
        MAFIEU(5, ChatColor.GRAY + "Mafieu"),
        TRAFIQUANT(6, ChatColor.LIGHT_PURPLE + "Trafiquant"),
        DEALER(7, ChatColor.DARK_PURPLE + "Dealer"),
        ACTUALISER(8, ChatColor.GREEN + "Actualiser la liste des jobs"),
        TUEUR_A_GAGES(10, ChatColor.GOLD + "Tueur à gages"),
        CUISINIER(11, ChatColor.YELLOW + "Cuisinier"),
        COMMERCANT(12, ChatColor.YELLOW + "Commercant"),
        MINEUR(13, ChatColor.GRAY + "Mineur"),
        SECURITE(14, ChatColor.BLUE + "Agent de sécurité"),
        DIR(15, ChatColor.GOLD + "DIR"),
        FORCES(16, ChatColor.AQUA + "Forces de l'ordre")
        ;
        private final int slot;
        private final String displayName;

        Metiers(int slot, String displayName)
        {
            this.slot = slot;
            this.displayName = displayName;
        }

        public int getSlot()
        {
            return this.slot;
        }

        public String getDisplayName()
        {
            return this.displayName;
        }
    }
}
