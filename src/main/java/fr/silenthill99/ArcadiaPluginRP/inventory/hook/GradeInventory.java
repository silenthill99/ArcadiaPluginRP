package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.GradeHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GradeInventory extends AbstractInventory<GradeHolder>
{
    public GradeInventory()
    {
        super(GradeHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        GradeHolder holder = new GradeHolder(target);

        ItemStack Liste = new ItemBuilder(Material.YELLOW_DYE).setName(ChatColor.GREEN + "Actualiser la liste des jobs").toItemStack();
        Inventory inv = createInventory(holder, 9, "Grade | " + target.getName());
        int slot = 1;
        for (Grades grades : Grades.values())
        {
            holder.grades.put(slot, grades);
            inv.setItem(slot++, new ItemBuilder(Material.PAPER).setName(grades.getName()).toItemStack());
        }
        inv.setItem(7, Liste);
        inv.setItem(8, RETOUR);
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, GradeHolder holder)
    {
        OfflinePlayer target = holder.getTarget();
        Grades grades = holder.grades.get(event.getSlot());
        switch (current.getType())
        {
            case YELLOW_DYE:
            {
                player.closeInventory();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "list");
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

    public enum Grades
    {
        YOUTUBEUR(ChatColor.RED + "You" + ChatColor.WHITE + "Tube"),
        VIP(ChatColor.YELLOW + "VIP"),
        VIP_PLUS(ChatColor.YELLOW + "VIP+"),
        VIP_PLUS_PLUS(ChatColor.YELLOW + "VIP++");

        private final String name;

        Grades(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
