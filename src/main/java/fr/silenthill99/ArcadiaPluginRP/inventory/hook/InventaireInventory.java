package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.InventaireHolder;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventaireInventory extends AbstractInventory<InventaireHolder>
{
    public InventaireInventory()
    {
        super(InventaireHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        InventaireHolder holder = new InventaireHolder(target);
        Inventory inv = createInventory(holder, 36, "Fouiller " + target.getName());
        for (int slot = 0; slot <= 35; slot++)
        {
            if (!target.getPlayer().getInventory().getItem(slot).equals(Material.AIR))
            {
                inv.setItem(slot, target.getPlayer().getInventory().getItem(slot));
            }
        }
        player.openInventory(inv);
    }
}
