package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.RankUpSuperHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RankUpSuperinventory extends AbstractInventory<RankUpSuperHolder>
{
    public RankUpSuperinventory()
    {
        super(RankUpSuperHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        RankUpSuperHolder holder = new RankUpSuperHolder(target);

        ItemStack Resp_equipe = new ItemBuilder(Material.YELLOW_WOOL).setName(ChatColor.GOLD + "[" + ChatColor.YELLOW + "Resp. équipe" + ChatColor.GOLD + "]").toItemStack();
        ItemStack Directeur = new ItemBuilder(Material.RED_WOOL).setName(ChatColor.DARK_RED + "[" + ChatColor.RED + "Directeur" + ChatColor.DARK_RED + "]").toItemStack();

        Inventory inv = createInventory(holder, 9, "Rank | " + target.getName());
        inv.setItem(1, Resp_equipe);
        inv.setItem(2, Directeur);
        inv.setItem(3, RETOUR);
        player.openInventory(inv);
    }
}
