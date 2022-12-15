package fr.silenthill99.ArcadiaPluginRP.inventory.hook;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.PolicierHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PolicierInventory extends AbstractInventory<PolicierHolder>
{
    public PolicierInventory() {
        super(PolicierHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        PolicierHolder holder = new PolicierHolder(target);

        ItemStack Fouiller = new ItemBuilder(Material.BLAZE_ROD).setName(ChatColor.GREEN + "Fouiller").toItemStack();

        Inventory inv = createInventory(holder, 27, "Menu Policier");
        inv.setItem(0, Fouiller);
        player.openInventory(inv);
    }

    @Override
    public void onInteractEntity(PlayerInteractAtEntityEvent event)
    {
        if (!(event.getRightClicked() instanceof Player)) return;
        Player player = event.getPlayer();
        Player target = (Player) event.getRightClicked();
        if (!player.hasPermission("group.policier") || player.isOp() || target.hasMetadata("NPC")) return;
        InventoryManager.openInventory(player, InventoryType.POLICIER, target);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, PolicierHolder holder) {
        OfflinePlayer target = holder.getTarget();

        switch (current.getType())
        {
            case BLAZE_ROD:
            {
                InventoryManager.openInventory(player, InventoryType.INVENTAIRE, target);
                break;
            }
            default:
            {
                break;
            }
        }
    }
}
