package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.ErreurStaffHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ErreurStaffInventory extends AbstractInventory<ErreurStaffHolder> {
    public ErreurStaffInventory()
    {
        super(ErreurStaffHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        ErreurStaffHolder holder = new ErreurStaffHolder(target);

        Inventory inv = createInventory(holder, 18, "Erreur | " + target.getName());
        int slot = 1;
        for (ErreurStaff erreur_staff : ErreurStaff.values())
        {
            if (slot != 9)
            {
                holder.erreur_staff.put(slot, erreur_staff);
                inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(ChatColor.DARK_RED + erreur_staff.getName()).setLore(erreur_staff.getLore()).toItemStack());
            }
            else
            {
                slot = 10;
                holder.erreur_staff.put(slot, erreur_staff);
                inv.setItem(slot, new ItemBuilder(Material.PAPER).setName(ChatColor.DARK_RED + erreur_staff.getName()).setLore(erreur_staff.getLore()).toItemStack());
            }
        }
        inv.setItem(17, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ErreurStaffHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        ErreurStaff erreur_staff = holder.erreur_staff.get(event.getSlot());
        switch (current.getType())
        {
            case PAPER:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(player, "warn " + target.getName() + " Erreur staff | " + erreur_staff.getName());
                break;
            }
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
                break;
            }
        }
    }

    public enum ErreurStaff
    {
        FLY_SANS_VANISH("Fly sans vanish"),
        GOD_EN_WZ("God en WZ", "Seulement si vanish désactivé"),
        ABUS_DE_POUVOIR("Abus de pouvoir", "Passible d'un DéRank immédiat"),
        ABUS_DE_PERMS("Abus de permissions"),
        HIERARCHIE("Hiérarchie"),
        FREEBAN("FreeBan"),
        FREEWARN("FreeWarn"),
        NON_RESPECT_REGLEMENT_STAFF("Non-Respect | Règlement Staff"),
        ABSENCE_NON_JUSTIFIEE("Absence non justifiée");

        private final String name;
        private final String[] lore;

        ErreurStaff(String name, String...lore)
        {
            this.name = name;
            this.lore = lore;
        }

        public String getName()
        {
            return this.name;
        }

        public String[] getLore()
        {
            return this.lore;
        }
    }
}
