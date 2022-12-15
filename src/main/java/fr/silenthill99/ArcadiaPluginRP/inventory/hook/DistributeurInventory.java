package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.DistributeurHolder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class DistributeurInventory extends AbstractInventory<DistributeurHolder>
{
    public DistributeurInventory()
    {
        super(DistributeurHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        DistributeurHolder holder = new DistributeurHolder();
        Inventory inv = createInventory(holder, 27, "Distributeur");
        player.openInventory(inv);
    }

    @Override
    public void onInteractWithBlock(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        if (action.equals(Action.RIGHT_CLICK_BLOCK))
        {
            if (block.getType().equals(Material.DROPPER))
            {
                event.setCancelled(true);
                openInventory(player);
            }
        }
    }
}
