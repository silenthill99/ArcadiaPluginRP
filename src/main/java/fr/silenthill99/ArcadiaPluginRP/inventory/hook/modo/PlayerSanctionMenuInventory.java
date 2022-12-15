package fr.silenthill99.ArcadiaPluginRP.inventory.hook.modo;

import fr.silenthill99.ArcadiaPluginRP.ItemBuilder;
import fr.silenthill99.ArcadiaPluginRP.inventory.AbstractInventory;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryManager;
import fr.silenthill99.ArcadiaPluginRP.inventory.InventoryType;
import fr.silenthill99.ArcadiaPluginRP.inventory.holder.modo.PlayerSanctionMenuHolder;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerSanctionMenuInventory extends AbstractInventory<PlayerSanctionMenuHolder> {
    public PlayerSanctionMenuInventory()
    {
        super(PlayerSanctionMenuHolder.class);
    }

    @Override
    public void openInventory(Player player, Object... args)
    {
        OfflinePlayer target = (OfflinePlayer) args[0];
        PlayerSanctionMenuHolder holder = new PlayerSanctionMenuHolder(target);

        ItemBuilder Administration = new ItemBuilder(Material.BLAZE_POWDER).setName("§aAdministration");
        ItemBuilder Ajouter_une_sanction = new ItemBuilder(Material.STONE).setName("§aAjouter une sanction");
        ItemBuilder Option = new ItemBuilder(Material.BLAZE_ROD).setName("§aOption");
        ItemBuilder TETE = new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(target.getName()).setName("§c§o"+target.getName());

        Inventory inv = createInventory(holder, 9, "Panel de " + target);
        inv.setItem(1, Administration.toItemStack());
        inv.setItem(3, Ajouter_une_sanction.toItemStack());
        inv.setItem(5, Option.toItemStack());
        inv.setItem(8, TETE.toItemStack());
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, PlayerSanctionMenuHolder holder) {
        OfflinePlayer target = holder.getTarget();
        switch (current.getType())
        {
            case BLAZE_POWDER:
            {
                if (!player.hasPermission("admin.staff"))
                {
                    player.sendMessage("&a[Administration] &cVous n'avez pas accès à ceci");
                }
                else
                {
                    InventoryManager.openInventory(player, InventoryType.PLAYER_ADMIN_MENU, target);
                }
                break;
            }
            case STONE:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_SANCTION_INVENTORY, target);
                break;
            }
            case BLAZE_ROD:
            {
                InventoryManager.openInventory(player, InventoryType.PLAYER_OPTIONS, target);
                break;
            }
            default:
            {
                break;
            }
        }
    }
}
