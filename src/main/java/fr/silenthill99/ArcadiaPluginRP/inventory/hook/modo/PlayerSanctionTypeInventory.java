package fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.modo.PlayerSanctionTypeHolder;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerSanctionTypeInventory extends AbstractInventory<PlayerSanctionTypeHolder>
{
    public PlayerSanctionTypeInventory()
    {
        super(PlayerSanctionTypeHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args) 
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        PlayerSanctionTypeHolder holder = new PlayerSanctionTypeHolder(target);
        ItemBuilder Avertir = new ItemBuilder(Material.GREEN_DYE).setName("§aAvertir le joueur");
        ItemBuilder Bannir = new ItemBuilder(Material.RED_DYE).setName("§4Bannir le joueur");
        ItemBuilder Ejecter = new ItemBuilder(Material.BLUE_DYE).setName("§3Expulser le joueur");
        ItemBuilder Retour = new ItemBuilder(Material.NETHER_STAR).setName("§cRetour");

        Inventory inv = createInventory(holder, 9, "§2Sanctionner "+target.getName());
        inv.setItem(2, Avertir.toItemStack());
        inv.setItem(4, Bannir.toItemStack());
        inv.setItem(6, Ejecter.toItemStack());
        inv.setItem(8, Retour.toItemStack());
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, PlayerSanctionTypeHolder holder) {
        OfflinePlayer target = holder.getTarget();
        switch (current.getType())
        {
            case GREEN_DYE:
            {
                InventoryManager.openInventory(player, InventoryType.AVERTIR, target);
                break;
            }
            case RED_DYE:
            {
                InventoryManager.openInventory(player, InventoryType.BAN, target);
                break;
            }
            case BLUE_DYE:
            {
                InventoryManager.openInventory(player, InventoryType.EXPULSER, target);
                break;
            }
            case SUNFLOWER:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_MENU, target);
                break;
            }
        }
    }
}
